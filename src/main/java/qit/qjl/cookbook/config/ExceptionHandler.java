package qit.qjl.cookbook.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (c) QS AJ JM TEAM., Ltd. 2021-5-18. All rights reserved.
 *
 * 统一异常处理
 *
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@ControllerAdvice
public class  ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public String exceptionHandler(Exception e, HttpServletRequest request) throws Exception {

        Map<String, Object> map = new LinkedHashMap<>();

        String exceptionName = e.getClass().getSimpleName();
        log.info("excption :" + exceptionName);
        log.error(e.getMessage());
        switch (exceptionName) {
            case "RedisConnectionFailureException":
            case "CannotCreateTransactionException":
            case "InvalidDataAccessResourceUsageException":
                map.put("message", "系统错误");
                request.setAttribute("javax.servlet.error.status_code", 400);
                break;
            case "MissingRequestCookieException":
                map.put("message", "请登录后操作");
                map.put("error", "User Verification Failed");
                request.setAttribute("javax.servlet.error.status_code", 220);
                break;
            case "DishnameNullException":
                map.put("message", e.getMessage());
                map.put("error", "Wrong Access");
                request.setAttribute("javax.servlet.error.status_code", 300);
                break;
            case "NullPointerException":
                map.put("message", "文件不存在");
                request.setAttribute("javax.servlet.error.status_code", 400);
                break;
            case "EntityNotFoundException":
                map.put("message", "请检查输入参数");
                request.setAttribute("javax.servlet.error.status_code", 400);
                break;
            default:
                map.put("message", e.getMessage());
                request.setAttribute("javax.servlet.error.status_code", 400);
        }
        request.setAttribute("exMap", map);

        return "forward:/error";

    }

}
