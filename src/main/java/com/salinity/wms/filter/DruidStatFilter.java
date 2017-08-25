package com.salinity.wms.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Druid的StatFilter
 * Created by Administrator on 2017/6/13.
 */
@WebFilter(filterName = "druidStatFilter",urlPatterns = "/*",
initParams = {
        @WebInitParam(name="exclusions",value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {
}
