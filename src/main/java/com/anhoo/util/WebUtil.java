package com.anhoo.util;

import com.anhoo.common.BaseBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
public final class WebUtil extends BaseBean {


    /**
     * 向页面添加css
     *
     * @param cssPath
     * @return
     */
    public static String addCss(String cssPath) {
        try {
            if (RequestContextHolder.getRequestAttributes() != null) {
                ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
                if (servletRequestAttributes != null) {
                    HttpServletRequest request = servletRequestAttributes.getRequest();
                    return "<link rel=\"Stylesheet\" type=\"text/css\" href=\"" + RequestUtil.getBaseUrl(request) + cssPath + "\" />";
                }
            }
            return "<link rel=\"Stylesheet\" type=\"text/css\" href=\"" + cssPath + "\" />";
        } catch (Exception e) {
            logger.error("addCss Error:", e);
        }
        return "";
    }

    /**
     * 向页面添加javascript
     *
     * @param jsPath
     * @return
     */
    public static String addJs(String jsPath) {
        try {
            if (RequestContextHolder.getRequestAttributes() != null) {
                ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
                if (servletRequestAttributes != null) {
                    HttpServletRequest request = servletRequestAttributes.getRequest();
                    return "<script type=\"text/javascript\" src=\"" + RequestUtil.getBaseUrl(request) + jsPath + "\"></script>";
                }
            }
            return "<script type=\"text/javascript\" src=\"" + jsPath + "\"></script>";
        } catch (Exception e) {
            logger.error("addJs Error:", e);
        }
        return "";
    }

    /**
     * 获取基路径（类似http://localhost:8080/ctas/）
     *
     * @return
     */
    public static String getBaseUrl() {
        try {
            if (RequestContextHolder.getRequestAttributes() != null) {
                ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
                if (servletRequestAttributes != null) {
                    HttpServletRequest request = servletRequestAttributes.getRequest();
                    return RequestUtil.getBaseUrl(request);
                }
            }
        } catch (Exception e) {
            logger.error("getBaseUrl Error:", e);
        }
        return "";
    }

//    public static boolean endsWith(String src, String endStr) {
//        return src.endsWith(endStr);
//    }
//
//    public static boolean startWith(String src, String startStr) {
//        return src.startsWith(startStr);
//    }
//
//    public static boolean contain(String src, String targetSrc) {
//        return src.contains(targetSrc);
//    }
//
//    public static int indexOf(String src, String targetSrc) {
//        return src.indexOf(targetSrc);
//    }


}
