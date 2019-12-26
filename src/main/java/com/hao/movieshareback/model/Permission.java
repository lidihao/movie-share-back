package com.hao.movieshareback.model;

public class Permission extends BaseModel{
    private Integer permissionId;
    private String permissionName;
    private String permissionDes;
    private String permissionAction;

    public Permission() {
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDes() {
        return permissionDes;
    }

    public void setPermissionDes(String permissionDes) {
        this.permissionDes = permissionDes;
    }

    public String getPermissionAction() {
        return permissionAction;
    }

    public void setPermissionAction(String permissionAction) {
        this.permissionAction = permissionAction;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Permission{");
        sb.append("permissionId=").append(permissionId);
        sb.append(", permissionName='").append(permissionName).append('\'');
        sb.append(", permissionDes='").append(permissionDes).append('\'');
        sb.append(", permissionAction='").append(permissionAction).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
