package qit.qjl.cookbook.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.config.AliSmsKey;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.exception.PhoneFormatException;
import qit.qjl.cookbook.exception.ShortSendException;
import qit.qjl.cookbook.util.RedisUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Service
@Slf4j
public class ShortMessageServiceImpl implements ShortMessageService {

    /**
     * 用户短信验证
     * @param phoneNumber
     * @return
     * @throws ClientException
     */
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AliSmsKey aliSmsKey;

    @Override
    public boolean sendShortMessage(String phoneNumber) throws ClientException {

        if (phoneNumber.length()!=11) {
            throw new PhoneFormatException();
        }
        for (int i=0;i<phoneNumber.length();i++){
            char ch = phoneNumber.charAt(i);
            if (!Character.isDigit(ch)) {
                throw new PhoneFormatException();
            }
        }

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsKey.getAccess(), aliSmsKey.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("SignName", "雪芒");
        request.putQueryParameter("TemplateCode", "SMS_202823002");
        request.putQueryParameter("PhoneNumbers", phoneNumber);

        Map<String, Object> params = new HashMap<>();
        String code = String.valueOf(Math.random()).substring(3, 9);
        params.put("code", code);

        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request);
            redisUtil.set(Content.REDIS_LOGIN_VERIFICATION+phoneNumber, code, 300);
            System.out.println(response.getData());
            if (!response.getHttpResponse().isSuccess()) {
                throw new ShortSendException();
            }
        } catch (ClientException e) {
            log.error("error:"+e.getClass().getSimpleName());
            log.error("message:"+e.getMessage());
            throw e;
        }
        return true;
    }

    @Override

    public boolean verification(String phone, String code) {

        if (phone.length()!=11) {
            throw new PhoneFormatException();
        }
        for (int i=0;i<phone.length();i++){
            char ch = phone.charAt(i);
            if (!Character.isDigit(ch)) {
                throw new PhoneFormatException();
            }
        }

        if (code==null||code.length()!=6||!redisUtil.hasKey(Content.REDIS_LOGIN_VERIFICATION+phone)) {
            return false;
        }

        String str = (String)redisUtil.get(Content.REDIS_LOGIN_VERIFICATION+phone);
        log.info("code:"+code);
        boolean flag = str.equals(code);
        if (flag) {
            if (redisUtil.hasKey(Content.REDIS_LOGIN_VERIFICATION+phone)) {
                redisUtil.del(Content.REDIS_LOGIN_VERIFICATION+phone);
            }
        }
        return flag;
    }

}
