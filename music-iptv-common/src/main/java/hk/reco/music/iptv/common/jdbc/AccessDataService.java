package hk.reco.music.iptv.common.jdbc;

import java.util.List;

import hk.reco.music.iptv.common.domain.IptvRes;
import hk.reco.music.iptv.common.wthx.QmMv;
import hk.reco.music.iptv.common.wthx.QmResourceService;
import hk.reco.music.iptv.common.wthx.QmSong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 数据访问服务,用于更新reco_music的一些数据到iptv新版库
 * @author zhangsl
 * @date 2019年6月21日
 */
@Service
public class AccessDataService {

    @Autowired
    protected IptvJdbcService iptvService;
    @Autowired
    protected QmResourceService qmService;

    public void addPlayTime() {//将以前没有时长的歌曲及mv补上playtime属性
    	List<IptvRes> songs = this.iptvService.findAllSong();
    	for(IptvRes song : songs){
    		long res_id = song.getRes_id();
    		QmSong qms = this.qmService.findSongByResid(res_id);
    		int playtime = qms.getSong_play_time();
    		this.iptvService.updateResPlaytime(song.getRid(), playtime);
    		System.out.println("update song playtime:"+ song.getRid());
    	}
    	List<IptvRes> mvs = this.iptvService.findAllMv();
    	for(IptvRes mv : mvs){
    		long res_id = mv.getRes_id();
    		QmMv qmvs = this.qmService.findMvByResid(res_id);
    		System.out.println(res_id);
    		int playtime = qmvs.getMv_play_time();
    		this.iptvService.updateResPlaytime(mv.getRid(), playtime);
    		System.out.println("update mv playtime:"+ mv.getRid());
    	}
    	
    }

}
