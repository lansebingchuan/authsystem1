package com.zht.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zht
 * @create 2019-12-08 16:49
 */
public class CommonUtil {

    /**
     * 判断一个数组是否为空
     * @param objects
     * @return
     */
    public static boolean flageArrayIsNotNull(Object[] objects){
        if (objects != null && objects.length > 0){
            return true;
        }
        return false;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String[] formatStringAtToArray (String s){
        if (s != null && !"".equals(s)){
            String[] split = s.split("&");
            return split;
        }
        return null;
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String formatDateToString(Date date){
        String str = "yyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        return sdf.format(date);
    }
}
