package com.winchance.wechat.badface.server.web;

public class ErrorInfo extends com.winchance.dataprotocol.ErrorInfo {
    public static final ErrorInfo PARSE_PARAMS_ERROR = new ErrorInfo(0x1000L, "PARSE_PARAMS_ERROR");

    public static final ErrorInfo PARAMS_NOT_FULL = new ErrorInfo(0x2000L, "PARAMS_NOT_FULL");

    public static final ErrorInfo IO_EXCEPTION = new ErrorInfo(0xFFFFL, "IO_EXCEPTION");

    protected ErrorInfo(Long code, String msg) {
        super(code, msg);
    }
}
