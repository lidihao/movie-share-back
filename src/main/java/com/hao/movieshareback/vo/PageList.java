package com.hao.movieshareback.vo;

import java.util.ArrayList;
import java.util.Collection;

public class PageList<T> extends ArrayList<T> {

    private PageInfo pageInfo;

    public PageList(){

    }

    public PageList(Page page, Collection<? extends T> e) {
        super(e);
        this.pageInfo = new PageInfo(page);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }



    @Override
    public String toString() {
        return "PageList{" +
                "pageInfo=" + pageInfo +
                '}';
    }
}
