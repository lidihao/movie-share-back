package com.hao.movieshareback.model.type;

import java.io.Serializable;

public enum  ApprovalType implements Serializable {
    PROCESSING("处理中",0),REJECT("拒绝",1),PASS("通过",2);
    private String desc;
    private int tag;

    ApprovalType(String desc, int tag) {
        this.desc = desc;
        this.tag = tag;
    }

    public static ApprovalType getApprovalTypeByTag(int tag){
        ApprovalType[] approvalTypes = ApprovalType.values();
        for (int i=0;i<approvalTypes.length;i++){
            if (tag==approvalTypes[i].tag){
                return approvalTypes[i];
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
