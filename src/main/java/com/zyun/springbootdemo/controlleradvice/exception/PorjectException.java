package com.zyun.springbootdemo.controlleradvice.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author zhaoyun
 * @since 2018-11-30
 */
@Data
public class PorjectException extends RuntimeException {

    private String code;
    private String msg;

    public PorjectException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}