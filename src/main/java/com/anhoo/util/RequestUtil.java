package com.anhoo.util;


import com.anhoo.common.BaseBean;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil extends BaseBean {

    private RequestUtil() {
        //工具类无需对象实例化
    }

    /**
     * 获取当前请求的基地址（例如：http://localhost:8080/ctas/xxx.do 返回 http://localhost:8080/ctas/）
     *
     * @param request
     * @return
     */
    public static String getBaseUrl(HttpServletRequest request) {
        return request.getRequestURL().substring(0,
                request.getRequestURL().indexOf(request.getContextPath()) + request.getContextPath().length()) + "/";

    }

    /**
     * 返回请求路径最后一段，比如http://localhost:8080/abc/index.do，返回index.do
     *
     * @param request
     * @return
     */
    public static String getUrlLastPart(HttpServletRequest request) {
        String[] arr = request.getServletPath().split("/");
        return arr[arr.length - 1];
    }

}
