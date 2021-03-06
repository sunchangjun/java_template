package hk.reco.music.iptv.common.layout;

import hk.reco.music.iptv.common.domain.IptvResVerForPage;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈mv布局参数〉
 *
 * @author comelk
 * @create 2019/4/17
 */
public class MvLayout  extends  DefaultRegularLayout{

    final private static Map<Integer,String> absolute = new HashMap<>();

    static{
        absolute.put(0,"7 0");
        absolute.put(1,"7 290");
        absolute.put(2,"7 580");
        absolute.put(3,"7 870");
        absolute.put(4,"180 0");
        absolute.put(5,"180 290");
        absolute.put(6,"180 580");
        absolute.put(7,"180 870");
    }

    public MvLayout(IptvResVerForPage ver) {
        super(ver);
        setAbsolute_map(absolute);
        setOneHeight(170);
        setWidth("280px");
        setHeight("159px");
        setTwoHeight(342);
        setOneline_num(4);
        setTwoline_num(8);
    }
}
