package hk.reco.music.iptv.common.layout;

import hk.reco.music.iptv.common.domain.IptvResVerForPage;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈分类布局参数〉
 *
 * @author comelk
 * @create 2019/4/17
 */
public class CateLayout extends  DefaultRegularLayout{

    private static  Map<Integer,String> absolute = new HashMap<>();

    static{
        absolute.put(0,"7px 0px");
        absolute.put(1,"7px 194px");
        absolute.put(2,"7px 388px");
        absolute.put(3,"7px 582px");
        absolute.put(4,"7px 776px");
        absolute.put(5,"7px 970px");
        absolute.put(6,"89px 0px");
        absolute.put(7,"89px 194px");
        absolute.put(8,"89px 388px");
        absolute.put(9,"89px 582px");
        absolute.put(10,"89px 776px");
        absolute.put(11,"89px 970px");
    }


    public CateLayout(IptvResVerForPage ver) {
        super(ver);
        setAbsolute_map(absolute);
        setOneHeight(110);
        setWidth("180px");
        setHeight("90px");
        setTwoHeight(170);
    }


}
