package com.salinity.wms.common.web;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/12.
 */
public class PageResponse<T> implements Serializable {

    Integer total;
    T rows;

    public PageResponse() {
    }

    public PageResponse(T rows,Integer total) {
        this.rows = rows;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
