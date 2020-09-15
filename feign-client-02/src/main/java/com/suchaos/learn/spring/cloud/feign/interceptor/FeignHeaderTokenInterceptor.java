package com.suchaos.learn.spring.cloud.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 将 Header 中关于携带 Token 的参数传递出去
 *
 * @author suchao
 * @date 2020/9/10
 */
@Component
public class FeignHeaderTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        System.out.println("FeignHeaderTokenInterceptor 被调用");
        if (getHttpServletRequest() == null) {
            return;
        }
        System.out.println("token:" + getHttpServletRequest().getHeader("oauthToken"));
        // 将获取Token对应的值往下面传
        //template.header("oauthToken", getHeaders(getHttpServletRequest()).get("oauthtoken"));
        template.header("oauthtoken", getHeaders(getHttpServletRequest()).get("oauthtoken"));
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Feign拦截器拦截请求获取Token对应的值
     *
     * @param request
     * @return
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();

        /*
            https://stackoverflow.com/questions/5258977/are-http-headers-case-sensitive

            1. http header 是 case-insensitive 的
            2. method 是 case-sensitive 的
         */

        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
