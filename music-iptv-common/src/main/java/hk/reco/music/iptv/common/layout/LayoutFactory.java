package hk.reco.music.iptv.common.layout;

import hk.reco.music.iptv.common.domain.IptvResVerForPage;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;

/**
 * 〈处于产生布局类〉
 *
 * @author comelk
 * @create 2019/4/17
 */
public class LayoutFactory {

    private IptvResVerForPage page;

    public LayoutFactory(IptvResVerForPage page) {
        this.page = page;
    }

    public Layout getLayout(){
         if(page.getChild_type().equals(IptvObjectEnum.mv)){
              return new MvLayout(page);
         }else if(page.getChild_type().equals(IptvObjectEnum.cate)){
              return new CateLayout(page);
         }else{
              return new DefaultRegularLayout(page);
         }
    }
}
