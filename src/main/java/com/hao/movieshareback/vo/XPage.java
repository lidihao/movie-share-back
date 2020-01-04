package com.hao.movieshareback.vo;

import java.util.List;

public class XPage<T> {

    public XPage() {
    }

    private PageInfo pageInfo;
    private List<T> result;

    public static <T> XPage<T> wrap(PageList<T> pageList) {
        return newInstance(pageList, pageList.getPageInfo());
    }

    public static <T> XPage<T> newInstance(List<T> result, PageInfo pageInfo) {
        return new XPage<T>(result, pageInfo);
    }

    public XPage(List<T> result, PageInfo pageInfo) {
        this.result = result;
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
