package com.salinity.wms.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用注解标注过滤器
 * @WebFilter 讲一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称，可选
 * 属性urlPatterns指定要过滤的URL模式，也可使用属性value来声明必选属性
 * @Order 用来表示过滤器的等级，由低到高顺序执行
 *
 * Created by Administrator on 2017/10/12.
 */
@WebFilter(filterName = "setCharacterEncodingFilter", urlPatterns = "/*")
@Order(0)
public class SetCharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("setCharacterEncodingFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 类型转换
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        // 设置请求格式为utf-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("setCharacterEncodingFilter destroy");
    }
}
