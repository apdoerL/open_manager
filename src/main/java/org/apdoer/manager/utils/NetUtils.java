package org.apdoer.manager.utils;



import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class NetUtils {

private static final String LOCALHOST = "127.0.0.1";

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                XFor =  XFor.substring(0, index);
            }
            if ("0:0:0:0:0:0:0:1".equals(XFor)) {
                XFor = "127.0.0.1";
            }
            return XFor;
        }
        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(XFor)) {
            XFor = "127.0.0.1";
        }
        return XFor;
    }


    /**
     * 获取登录用户地址
     *
     * @param
     * @return
     */
    public static String getAddByIp(String ip) {
        //淘宝IP地址库：http://ip.taobao.com/instructions.php
        String add = null;
        if (LOCALHOST.equals(ip)) {
            return "本机";
        }
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            URLConnection connection = U.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();

            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String, Object> map = (Map) jsonObject;
            //0：成功，1：失败。
            String code = String.valueOf(map.get("code"));
            if ("1".equals(code)) {
                //错误信息
                String data = String.valueOf(map.get("data"));
                log.error(data);
            } else if ("0".equals(code)) {
                Map<String, Object> data = (Map<String, Object>) map.get("data");
                //国家
                String country = String.valueOf(data.get("country"));
                String area = String.valueOf(data.get("area"));
                //市（县）
                String city = String.valueOf(data.get("city"));
                //省（自治区或直辖市）
                String region = String.valueOf(data.get("region"));
                add = getLocation(country,region,city);
            }
            return add;
        } catch (IOException e1) {
			log.error("exception:",e1);
			return "未知";
        }
    }

    private static String getLocation(String country,String region,String city){
        StringBuffer location = new StringBuffer("");
        if(!"XX".equals(location)){
            location.append(country);
        }
        if(!"XX".equals(region)){
            location.append(region);
        }
        if(!"XX".equals(city)){
            location.append(city);
        }
        return location.toString();
    }

}