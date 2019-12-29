package com.hao.movieshareback.vo;

public interface IBaseErrorInfo {
    /** 错误码*/
    Integer getResultCode();

    /** 错误描述*/
    String getResultMsg();
}