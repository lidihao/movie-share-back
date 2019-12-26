package com.hao.movieshareback.model;


/**
 * `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `role_name` VARCHAR(100) NOT NULL COMMENT '角色名称',
 *     `role_des` VARCHAR(100) NOT NULL COMMENT '角色描述',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (role_id),
 *     INDEX (is_delete)
 */
public class Role extends BaseModel{
    private Integer roleId;
    private String roleName;
    private String roleDes;

    public Role() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDes() {
        return roleDes;
    }

    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("roleId=").append(roleId);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", roleDes='").append(roleDes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
