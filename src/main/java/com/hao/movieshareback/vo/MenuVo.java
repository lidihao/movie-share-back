package com.hao.movieshareback.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuVo {
    private Integer menuId;
    private String menuName;
    private String menuEng;
    private String menuDes;
    private String menuUrl;
    private boolean hasChild;
    private List<MenuVo> menuVoList;

    public MenuVo(Integer menuId,String menuName, String menuEng, String menuDes, String menuUrl,boolean hasChild) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuEng = menuEng;
        this.menuDes = menuDes;
        this.menuUrl = menuUrl;
        this.hasChild = hasChild;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
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

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<MenuVo> getMenuVoList() {
        return menuVoList;
    }

    public void setMenuVoList(List<MenuVo> menuVoList) {
        this.menuVoList = menuVoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuVo{");
        sb.append("menuName='").append(menuName).append('\'');
        sb.append(", menuEng='").append(menuEng).append('\'');
        sb.append(", menuDes='").append(menuDes).append('\'');
        sb.append(", menuUrl='").append(menuUrl).append('\'');
        sb.append(", menuVoList=").append(menuVoList);
        sb.append('}');
        return sb.toString();
    }
}
