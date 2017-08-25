package com.salinity.wms.common;

/**
 * Created by Administrator on 2017/6/8.
 */
public class BasePo {
    private String orderBy;
    private Integer offSet = 0;
    private Integer page = 1;
    private Integer pageSize = 30;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getOffSet() {
        if(pageSize <= 0){
            pageSize = 1;
        }
        this.offSet = (page -1)*pageSize;
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
