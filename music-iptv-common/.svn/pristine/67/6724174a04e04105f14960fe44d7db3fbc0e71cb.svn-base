package hk.reco.music.iptv.common.utils;

import java.util.UUID;

/**
 * @ClassName ResIdUtil
 * @Description 工具
 * @Author Alistair.Chow
 * @date 2018/8/29 17:47
 * @Version 1.0
 */
public class ResIdUtil {
    public static String generateID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
    * @Author Alistair.Chow
    * @Description 生成前缀 + 随机UUID
    * @Date 16:26 2018/9/6
    * @Param [prefix, length]
    * @return java.lang.String
    **/
    public static String generateIptvID(String prefix, Integer length){
        String uuid = generateID();
        Integer size = length - prefix.length();
        return prefix + uuid.substring(0, size);
    }

    /**
    * @Author Alistair.Chow
    * @Description 前缀+补零+value
    * @Date 10:40 2018/10/17
    * @Param [prefix, value, length]
    * @return java.lang.String
    **/
    public static String generateIptvID(String prefix, String value, Integer length){
        Integer size = length - prefix.length();
        return prefix + addZeroForNum(value, size, true);
    }

    /**
     *
     *  解析generateId方法生成的ID
     *
     * @Author wangpq
     * @Param [iptv_code]
     * @Date 2019/4/2 18:01
     * @return long
     */
    public static long parseId(String iptv_code){
        if(iptv_code==null){
            throw new IllegalArgumentException("iptv_code cannot be null");
        }
        if(iptv_code.length()!=32){
            throw new IllegalArgumentException("iptv_code length must be 32");
        }
        return Long.parseLong(iptv_code.substring(19));
    }

    /**
    * @Author Alistair.Chow
    * @Description 生成ID， prifix(4位) + resId(14位) + musicId(14位)
    * @Date 15:49 2018/9/6
    * @Param [prefix, resId, musicId, length]
    * @return java.lang.String
    **/
    public static String generateId(String prefix, Long resId, Long musicId, Integer length){
        Integer size = length - prefix.length();
        // TODO: 未处理单数
        size = size / 2;
        return prefix + addZeroForNum(resId.toString(), size, true) + addZeroForNum(musicId.toString(), size, true);
    }

    /**
    * @Author Alistair.Chow
    * @Description 数字补零
    * @Date 11:46 2018/9/25
    * @Param [str, length, isLeft]
    * @return java.lang.String
    **/
    public static String addZeroForNum(String str,int length, boolean isLeft) {
        int strLen = str.length();
        if (strLen < length) {
            while (strLen < length) {
                StringBuffer sb = new StringBuffer();
                if(isLeft) {
                    sb.append("0").append(str);//左补0
                }else {
                    sb.append(str).append("0");//右补0
                }
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }


}
