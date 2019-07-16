package hk.reco.music.iptv.common.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 由文件头判断文件的格式,从而返回后缀
 * @author zhangsl
 * @date 2019年2月22日
 */
public class ImageTypeUtils {
	
	public static final String TYPE_JPG = "jpg";
    public static final String TYPE_GIF = "gif";
    public static final String TYPE_PNG = "png";
    public static final String TYPE_BMP = "bmp";
    public static final String TYPE_UNKNOWN = null;
    
    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){    
           StringBuilder stringBuilder = new StringBuilder();    
           if (src == null || src.length <= 0) {    
               return null;    
           }    
           for (int i = 0; i < src.length; i++) {    
               int v = src[i] & 0xFF;    
               String hv = Integer.toHexString(v);    
               if (hv.length() < 2) {    
                   stringBuilder.append(0);    
               }    
               stringBuilder.append(hv);    
           }    
           return stringBuilder.toString();    
       }
    
    
    /**
     * 根据文件字节判断图片类型
     * @param bs
     * @return jpg/png/gif/bmp
     */
    public static String getPicType(byte[] bs) {
    	byte[] b = new byte[4];
    	System.arraycopy(bs, 0, b, 0, 4);
        String head = bytesToHexString(b).toUpperCase();
        return parseHeader(head);
    }

    /**
     * 根据文件流判断图片类型
     * @param fis
     * @return jpg/png/gif/bmp
     */
    public static String getPicType(InputStream fis) {
        try {
        	byte[] b = new byte[4];
            fis.read(b, 0, b.length);
            String head = bytesToHexString(b).toUpperCase();
            return parseHeader(head);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * 文件的head标志
     * @param head
     * @return 文件类型
     */
    private static String parseHeader(String head) {
    	if (head.contains("FFD8FF")) {
            return TYPE_JPG;
        } else if (head.contains("89504E47")) {
            return TYPE_PNG;
        } else if (head.contains("47494638")) {
            return TYPE_GIF;
        } else if (head.contains("424D")) {
            return TYPE_BMP;
        }else{
            return TYPE_UNKNOWN;
        }
    }
}