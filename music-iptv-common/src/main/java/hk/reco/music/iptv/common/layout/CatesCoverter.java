package hk.reco.music.iptv.common.layout;

import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.domain.IptvResVerForPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈分类转化工具〉
 *
 * @author comelk
 * @create 2019/4/18
 */
public class CatesCoverter {

    private static Integer line_number = 5;

    private static Integer margin_top = 40;

    private static String border_width = "206px";

    private static List<Integer> left = new ArrayList<>();

    static {
        left.add(0);
        left.add(236);
        left.add(472);
        left.add(708);
        left.add(944);
    }

    public static List<IptvResVerForPage> coverCates( List<IptvResVer> list){
        List<IptvResVerForPage> pagelist = new ArrayList<>();
        Integer top = 7;
        if(list!=null&&list.size()>0){
            for (int i=0;i< list.size();i++) {
                IptvResVerForPage page = new IptvResVerForPage(list.get(i));
                Integer num= i%line_number;
                Integer line = i/line_number;
                page.setWidth(border_width);
                page.setHeight(border_width);
                page.setAbsolute_x(top+line*(margin_top+206));
                page.setAbsolute_y(left.get(num));
                pagelist.add(page);
            }
        }
        return pagelist;
    }

}
