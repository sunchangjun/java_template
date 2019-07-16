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
        absolute.put(0,"7px 0px");
        absolute.put(1,"7px 290px");
        absolute.put(2,"7px 580px");
        absolute.put(3,"7px 870px");
        absolute.put(4,"180px 0px");
        absolute.put(5,"180px 290px");
        absolute.put(6,"180px 580px");
        absolute.put(7,"180px 870px");
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
