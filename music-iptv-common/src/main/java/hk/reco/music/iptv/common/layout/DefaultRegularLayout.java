package hk.reco.music.iptv.common.layout;

import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.domain.IptvResVerForPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈规则布局〉
 *
 * @author comelk
 * @create 2019/4/17
 */
public class DefaultRegularLayout implements Layout{

    private IptvResVerForPage ver;

    private  Integer oneHeight=180;

    private  Integer twoHeight=380;

    private  String width = "176px";

    private  String height="176px";

    private  Integer oneline_num = 6;

    private  Integer twoline_num = 12;

    private   Map<Integer,String> absolute_map = new HashMap<>();

    public DefaultRegularLayout(IptvResVerForPage ver) {
        this.ver = ver;
        absolute_map.put(0,"7 0");
        absolute_map.put(1,"7 194");
        absolute_map.put(2,"7 388");
        absolute_map.put(3,"7 582");
        absolute_map.put(4,"7 776");
        absolute_map.put(5,"7 970");
        absolute_map.put(6,"194 0");
        absolute_map.put(7,"194 194");
        absolute_map.put(8,"194 388");
        absolute_map.put(9,"194 582");
        absolute_map.put(10,"194 776");
        absolute_map.put(11,"194 970");
    }

    public Integer  doSetLayoutInfo(){
        List<IptvResVer> list = ver.getList();
        List<IptvResVerForPage> pagelist = new ArrayList<>();
        Integer layout_line = ver.getLayout_line();
        Integer base = layout_line==1?oneline_num:twoline_num;
        if(list!=null&&list.size()>0){
            for (int i=0;i< list.size();i++) {
                IptvResVerForPage page = new IptvResVerForPage(list.get(i));
                Integer num= i%base;
                page.setWidth(this.width);
                page.setHeight(this.height);
                page.setAbsolute_x(Integer.valueOf(absolute_map.get(num).split(" ")[0]));
                page.setAbsolute_y(Integer.valueOf(absolute_map.get(num).split(" ")[1]));
                pagelist.add(page);
            }
        }
        ver.setPage_list(pagelist);
        Integer height = layout_line == 1 ? oneHeight : twoHeight;
        ver.setHeight(String.valueOf(height));
        return height;
    }

    public IptvResVerForPage getVer() {
        return ver;
    }

    public void setVer(IptvResVerForPage ver) {
        this.ver = ver;
    }

    public Integer getOneHeight() {
        return oneHeight;
    }

    public void setOneHeight(Integer oneHeight) {
        this.oneHeight = oneHeight;
    }

    public Integer getTwoHeight() {
        return twoHeight;
    }

    public void setTwoHeight(Integer twoHeight) {
        this.twoHeight = twoHeight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getOneline_num() {
        return oneline_num;
    }

    public void setOneline_num(Integer oneline_num) {
        this.oneline_num = oneline_num;
    }

    public Integer getTwoline_num() {
        return twoline_num;
    }

    public void setTwoline_num(Integer twoline_num) {
        this.twoline_num = twoline_num;
    }

    public Map<Integer, String> getAbsolute_map() {
        return absolute_map;
    }

    public void setAbsolute_map(Map<Integer, String> absolute_map) {
        this.absolute_map = absolute_map;
    }
}
