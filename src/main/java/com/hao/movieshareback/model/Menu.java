package com.hao.movieshareback.model;

import java.util.Objects;

public class Menu extends BaseModel{
    public final static Integer ROOT_TAG = 0;
    private Integer menuId;
    private String menuName;
    private String menuEng;
    private String menuDes;
    private Integer sort;
    private String menuUrl;
    private Integer parentMenuId;
    private boolean hasChild;

    public Menu() {
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuEng() {
        return menuEng;
    }

    public void setMenuEng(String menuEng) {
        this.menuEng = menuEng;
    }

    public String getMenuDes() {
        return menuDes;
    }

    public void setMenuDes(String menuDes) {
        this.menuDes = menuDes;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("menuId=").append(menuId);
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuEng='").append(menuEng).append('\'');
        sb.append(", menuDes='").append(menuDes).append('\'');
        sb.append(", sort=").append(sort);
        sb.append(", menuUrl='").append(menuUrl).append('\'');
        sb.append(", parentMenuId='").append(parentMenuId).append('\'');
        sb.append(", hasChild=").append(hasChild);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menuId.equals(menu.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId);
    }
}
