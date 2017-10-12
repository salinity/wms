package com.salinity.wms.filter;

import com.salinity.wms.constant.WmsConstants;
import com.salinity.wms.pojo.domain.CurrentUser;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/12.
 */
@WebFilter(filterName = "wmsSessionFilter", urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions", value = "*.html,*.js,*.gif,*.jpg,*.bmp,*.png,*.ico,/login")
})
@Order(1)
public class WmsSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 强转
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        final HttpSession session = request.getSession(false);
        // 从session中获取用户信息
        CurrentUser currentUser = session == null ? null : (CurrentUser) session.getAttribute(WmsConstants.SESSION_KEY);
        if (currentUser != null) {
            filterChain.doFilter(request, response);
            return;
        }
        // 重定位到登录页
        response.sendRedirect("/#/login");

    }

    @Override
    public void destroy() {

    }
}
