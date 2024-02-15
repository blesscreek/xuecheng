package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @Author bless
 * @Version 1.0
 * @Description 和前端约定返回的异常信息模型
 * @Date 2024-02-16 0:09
 */

public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}