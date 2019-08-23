package hk.reco.music.iptv.common.utils;

import java.io.UnsupportedEncodingException;

/**
 * 〈字符串工具类〉
 *
 * @author comelk
 * @create 2019/7/31
 */
public class StringUtils {

    public static String getWordCount(String str,int len) {
        try {
            String symbol = "";
            int counterOfDoubleByte = 0;
            byte b[] = str.getBytes("GBK");
            if (b.length <= len)
                return str;
            for (int i = 0; i < len; i++) {
                if (b[i] < 0)
                    counterOfDoubleByte++;
            }
            if (counterOfDoubleByte % 2 == 0)
                return new String(b, 0, len, "GBK") + symbol;
            else
                return new String(b, 0, len - 1, "GBK") + symbol;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
