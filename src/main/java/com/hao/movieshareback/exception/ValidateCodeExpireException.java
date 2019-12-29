package com.hao.movieshareback.exception;

public class ValidateCodeExpireException extends RuntimeException{
    public ValidateCodeExpireException(String msg){
        super(msg);
    }
}
