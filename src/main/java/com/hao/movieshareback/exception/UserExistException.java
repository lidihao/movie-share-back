package com.hao.movieshareback.exception;

public class UserExistException extends RegisterException {
    public UserExistException(String msg){
        super(msg);
    }
}
