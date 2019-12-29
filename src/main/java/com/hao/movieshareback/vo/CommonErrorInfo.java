package com.hao.movieshareback.vo;

public class CommonErrorInfo implements IBaseErrorInfo {
    // 数据操作错误定义
    public final static CommonErrorInfo SUCCESS = new CommonErrorInfo(200, "成功!");
    public final static CommonErrorInfo BODY_NOT_MATCH= new CommonErrorInfo(400,"请求的数据格式不符!");
    public final static CommonErrorInfo SIGNATURE_NOT_MATCH=new CommonErrorInfo(401,"请求的数字签名不匹配!");
    public final static CommonErrorInfo NOT_FOUND=new CommonErrorInfo(404, "未找到该资源!");
    public final static CommonErrorInfo INTERNAL_SERVER_ERROR=new CommonErrorInfo(500, "服务器内部错误!");
    public final static CommonErrorInfo SERVER_BUSY=new CommonErrorInfo(503,"服务器正忙，请稍后再试!");

    public final static Integer SUCCESS_CODE=200;
    public final static Integer ERROR_CODE=500;

    /** 错误码 */
    private Integer resultCode;

    /** 错误描述 */
    private String resultMsg;

    public CommonErrorInfo(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}