package com.salinity.wms.common.annotation.impl;

import com.salinity.wms.common.annotation.RequestLimit;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/15.
 */
@Aspect
@Component
public class RequestLimitContract {

    private static final Logger logger = Logger.getLogger(RequestLimitContract.class);

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws Exception {
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i ++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if(request == null) {
                throw new Exception("方法中缺失HttpServletRequest参数");
            }
            String ip = getIPAddr(request);
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            long count = redisTemplate.opsForValue().increment(key,1);
            if (!redisTemplate.hasKey(key)) {
                redisTemplate.expire(key, limit.time(), TimeUnit.MICROSECONDS);
            }
            logger.info("key: " + key + ";  count: " + count);
            if (count > limit.count()) {
                logger.warn("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
            }
        }catch (Exception e) {
             logger.error("发生异常", e);
        }
     }

    /**
     * 获取请求的ip地址
     * @param request
     * @return
     */
    public String getIPAddr(HttpServletRequest request){
        if (request == null)
            return null;
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip))
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException unknownhostexception) {
            }
        return ip;
    }
}
