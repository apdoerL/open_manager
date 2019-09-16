package org.apdoer.manager.utils;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 七牛云存储工具类
 * @author apdoer
 * @date 2018-12-31
 */
public class QiNiuUtil {

    public static final String CHINA_EAST = "华东";

    public static final String CHINA_NORTH = "华北";

    public static final String CHINA_SOUTH = "华南";

    public static final String NORTH_AMRICA = "北美";

    /**
     * 得到机房的对应关系
     * @param zone
     * @return
     */
    public static Configuration getConfiguration(String zone){

        if(CHINA_EAST.equals(zone)){
            return new Configuration(Zone.zone0());
        } else if(CHINA_NORTH.equals(zone)){
            return new Configuration(Zone.zone1());
        } else if(CHINA_SOUTH.equals(zone)){
            return new Configuration(Zone.zone2());
        } else if (NORTH_AMRICA.equals(zone)){
            return new Configuration(Zone.zoneNa0());
            // 否则就是东南亚
        } else {
            return new Configuration(Zone.zoneAs0());
        }
    }

    /**
     * 默认不指定key的情况下，以文件内容的hash值作为文件名
     * @param file
     * @return
     */
    public static String getKey(String file){
        StringBuffer key = new StringBuffer(FileUtil.getFileNameNoEx(file));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        key.append("-");
        key.append(sdf.format(date));
        key.append(".");
        key.append(FileUtil.getExtensionName(file));
        return key.toString();
    }
}
