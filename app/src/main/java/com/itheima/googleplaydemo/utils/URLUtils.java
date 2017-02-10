package com.itheima.googleplaydemo.utils;

import com.itheima.googleplaydemo.app.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leon on 2017/1/8.
 */

public class URLUtils {

    /**
     * 拼接map参数为string
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    /**
     * range 已下载的大小
     */
    public static String getDownloadURL(String name, long range) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("range", range);
        return Constant.URL_DOWNLOAD + getUrlParamsByMap(map);
    }
}
