package org.example.snsproject.entity;

import org.example.snsproject.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.snsproject.utils.ResultUtil;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;


    public static <E> Result<E> success() {
        return new Result<>(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.message(), null);
    }

    public static <E> Result<E> success(E data) {
        return new Result<>(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.message(), data);
    }

    public static <E> Result<E> error(Integer errorCode) {
        return new Result<>(errorCode, ResultUtil.getMessage(errorCode), null);
    }

    public static <E> Result<E> error(Integer errorCode, E data) {
        return new Result<>(errorCode, ResultUtil.getMessage(errorCode), data);
    }
}