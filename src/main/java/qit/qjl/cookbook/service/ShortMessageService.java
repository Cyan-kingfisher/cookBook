package qit.qjl.cookbook.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface ShortMessageService {

    /**
     * 用户短信验证
     * @param phoneNumber
     * @return
     * @throws ClientException
     */
    boolean sendShortMessage(String phoneNumber) throws ClientException;
    boolean verification(String phone, String code);

}
