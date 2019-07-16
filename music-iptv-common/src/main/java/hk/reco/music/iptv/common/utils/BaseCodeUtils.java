package hk.reco.music.iptv.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

/**
 * @ClassName BaseCodeUtils
 * @Description TODO Base编码
 * @Author wangpq
 * @Date 2019/3/22 15:21
 * @Version 1.0
 */
public class BaseCodeUtils {

    private static final char[] UPPER = "0123456789ABCDEF".toCharArray();
    private static final char[] LOWER = "0123456789abcdef".toCharArray();
    private static final byte[] DECODE = new byte[128];

    static {

        for (int i = 0; i < 10; i++) {
            DECODE['0' + i] = (byte) i;
            DECODE['A' + i] = (byte) (10 + i);
            DECODE['a' + i] = (byte) (10 + i);
        }
    }

    /**
     * Base16 编码
     *
     * @return char[]
     * @Author wangpq
     * @Param [src, upper]
     * @Date 2019/3/22 15:30
     */
    public static char[] encodeBase16(byte[] content, boolean upper) {
        char[] table = upper ? BaseCodeUtils.UPPER : BaseCodeUtils.LOWER;
        char[] dst = new char[content.length * 2];
        for (int si = 0, di = 0; si < content.length; si++) {
            byte b = content[si];
            dst[di++] = table[(b & 0xf0) >>> 4];
            dst[di++] = table[(b & 0x0f)];
        }
        return dst;
    }

    /**
     * Base16 编码
     *
     * @return java.lang.String
     * @Author wangpq
     * @Param [content, upper]
     * @Date 2019/3/22 15:36
     */
    public static String encodeBase16(String content, boolean upper) {
        return String.valueOf(encodeBase16(content.getBytes(), upper));
    }

    /**
     * Base16 解码
     *
     * @return byte[]
     * @Author wangpq
     * @Param [src]
     * @Date 2019/3/22 15:32
     */
    public static byte[] decodeBase16(char[] src) {
        byte[] dst = new byte[src.length / 2];

        for (int si = 0, di = 0; di < dst.length; di++) {
            byte high = DECODE[src[si++] & 0x7f];
            byte low = DECODE[src[si++] & 0x7f];
            dst[di] = (byte) ((high << 4) + low);
        }
        return dst;
    }

    /**
     * Base16 解码
     *
     * @return java.lang.String
     * @Author wangpq
     * @Param [content]
     * @Date 2019/3/22 15:37
     */
    public static String decodeBase16(String content) {
        return StringUtils.newStringUtf8(decodeBase16(content.toCharArray()));
    }

    /**
     * Base64 编码
     *
     * @return java.lang.String
     * @Author wangpq
     * @Param [content]
     * @Date 2019/3/22 15:14
     */
    public static String encodeBase64(String content) {
        return Base64.encodeBase64String(content.getBytes());
    }

    /**
     * Base64 解码
     *
     * @return java.lang.String
     * @Author wangpq
     * @Param [content]
     * @Date 2019/3/22 15:15
     */
    public static String decodeBase64(String content) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(content));
    }

}
