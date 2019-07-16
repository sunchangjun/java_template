package hk.reco.music.iptv.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName QmResType
 * @Description TODO
 * @Author wangpq
 * @Date 2019/3/7 17:03
 * @Version 1.0
 */
public enum IptvResType {

    // val, name
    CATEGORY(1, "分类"),
    SONG(2, "歌曲"),
    SINGER(3, "歌手"),
    MV(4, "MV"),
    ALBUM(5, "专辑"),
    DISS(6, "歌单"),
    MIXED(7, "混合资源"),
    RADIO_GROUP(8, "电台组"),
    RADIO(9, "电台"),
    ;


    private Integer val;
    private String name;

    private IptvResType(Integer val, String name) {
        this.val = val;
        this.name = name;
    }

    public Integer getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, String> EnumMap = null;

    public static Map<Integer, String> getValueNameMap() {
        if (null == EnumMap) {
            EnumMap = new HashMap<Integer, String>();
            for (IptvResType fjydResTypeEnum : IptvResType.values()) {
                EnumMap.put(fjydResTypeEnum.getVal(), fjydResTypeEnum.getName());
            }
        }
        return EnumMap;
    }

    public static String getNameFromCode(Integer vue) {
        for (IptvResType fjydResTypeEnum : IptvResType.values()) {
            if (fjydResTypeEnum.getVal().equals(vue)) {
                return fjydResTypeEnum.getName();
            }
        }
        return "";
    }
}
