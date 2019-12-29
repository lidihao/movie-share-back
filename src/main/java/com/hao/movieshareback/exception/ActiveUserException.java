package com.hao.movieshareback.exception;

public class ActiveUserException extends RuntimeException{
    public ActiveUserException(String msg){
        super(msg);
    }
}
