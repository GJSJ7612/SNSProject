package org.example.snsproject.utils;

import org.example.snsproject.common.ResultCode;

public class ResultUtil {

    public static String getMessage(Integer code) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.code().equals(code)) {
                return resultCode.message();
            }
        }
        return "未知状态码";
    }
}