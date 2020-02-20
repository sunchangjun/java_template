package hk.reco.music.iptv.common.stats.chart.msic;

import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.domain.IptvStatsUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class IptvJdbcDao {

	@Autowired
	@Qualifier("iptvJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	private Map<Long,String> map = new HashMap<Long,String>();

	public IptvResVer findResByResid(long res_id) {
		String sql = "SELECT * FROM iptv_res r,iptv_res_ver v WHERE r.rid=v.rid AND r.res_id=?";
		try{
			return this.jdbcTemplate.queryForObject(sql, new Object[]{res_id}, new BeanPropertyRowMapper<>(IptvResVer.class));
		}catch(Exception e){
		    e.printStackTrace();
			return null;
		}
	}
    public List<IptvResVer> findResAllMvAndSong() {
        String sql = "SELECT * FROM iptv_res r,iptv_res_ver v WHERE r.rid=v.rid AND r.type in('mv','song') ";
        try{
            return this.jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(IptvResVer.class));
        }catch(Exception e){
            return null;
        }
    }
    public List<IptvResVer> findExcludeLinkAllResAndVer() {
        String sql = "SELECT * FROM iptv_res r,iptv_res_ver v WHERE r.test_vid=v.vid and r.type !='link' ";
        try{
            return this.jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(IptvResVer.class));
        }catch(Exception e){
            return null;
        }
    }

    public IptvResVer findResByRid(long rid) {
        String sql = "SELECT * FROM iptv_res r,iptv_res_ver v WHERE r.rid=v.rid AND r.rid=?";
        try{
            return this.jdbcTemplate.queryForObject(sql, new Object[]{rid}, new BeanPropertyRowMapper<>(IptvResVer.class));
        }catch(Exception e){
            return null;
        }
    }
	
	public List<IptvStatsResVer> findAllResVer() {
		String sql = "SELECT r.rid,r.type,v.name,v.link_prid,v.link_crid FROM iptv_res r,iptv_res_ver v WHERE r.rid=v.rid AND r.prod_vid=v.vid AND v.version_disable=0 ORDER BY rid";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IptvStatsResVer.class));
	}


	
	public List<IptvStatsUser> findStatsUser(String start, String end) {
		String sql = "SELECT * FROM iptv_stats_user WHERE date>=? AND date<=?";
		return this.jdbcTemplate.query(sql, new Object[]{start,end}, new BeanPropertyRowMapper<>(IptvStatsUser.class));
	}
	
	public List<IptvStatsResPlay> findStatsResPlay(String start, String end, String type) {
		String sql = "SELECT p.*,r.free,v.name,r.type FROM iptv_res_daily_play p,iptv_res r,iptv_res_ver v WHERE r.rid=p.rid AND r.rid=v.rid AND "
				+ "r.prod_vid=v.vid AND  p.daily BETWEEN ? AND ? AND r.type=? ORDER BY p.daily ASC,p.play_num DESC";
		return this.jdbcTemplate.query(sql, new Object[]{start,end,type}, new BeanPropertyRowMapper<>(IptvStatsResPlay.class));
	}
	
	public List<IptvStatsResPlay> findFoo(String start, String end, String type,int offset,int size) {
		String sql = "SELECT v.name,r.free,p.rid,SUM(p.play_num) total,GROUP_CONCAT(CONCAT(p.daily,':',p.play_num)) daily_play_num FROM iptv_res_daily_play p,iptv_res r,iptv_res_ver v "
				+ "WHERE r.rid=v.rid AND r.prod_vid=v.vid AND r.rid=p.rid AND r.type=? AND p.daily BETWEEN ? AND ? GROUP BY p.rid ORDER BY SUM(p.play_num) DESC LIMIT ?,?";
		return this.jdbcTemplate.query(sql, new Object[]{type,start,end,offset,size}, new BeanPropertyRowMapper<>(IptvStatsResPlay.class));
	}
	
	public IptvStatsResVer findResVer(long rid) {
		if(map.containsKey(rid)){
			System.out.println("exist==>"+rid);
		}else{
			map.put(rid, null);
		}
		
		String sql = "SELECT r.rid,r.type,v.name FROM iptv_res r,iptv_res_ver v WHERE r.rid=? AND r.rid=v.rid AND r.prod_vid=v.vid AND v.version_disable=0 ORDER BY rid";
		try{
			return this.jdbcTemplate.queryForObject(sql, new Object[]{rid}, new BeanPropertyRowMapper<>(IptvStatsResVer.class));
		}catch(Exception e){
			return null;
		}
	}

	public Integer findDsitinctVisitUser(String from, String to) {
		String sql = "SELECT COUNT(DISTINCT(user_id)) FROM iptv_stats_user_detail WHERE DATE BETWEEN ? AND ?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{from,to}, Integer.class);
	}
	
	public Integer findDsitinctPlayUser(String from, String to) {
		String sql = "SELECT COUNT(DISTINCT(user_id)) FROM iptv_stats_user_detail WHERE play_times!=0 AND DATE BETWEEN ? AND ?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{from,to}, Integer.class);
	}

    /*统计相关*/
    public List<Long> findAllMasterNode(){
        String sql="select rid  from iptv_res  where global_disable =0 and type='layout' and child_type='layout_custom'";
        return this.jdbcTemplate.queryForList(sql,Long.class);
    }
    public List<Long> findRidPlayHistoryByTime(String from,String to){
        String sql="select DISTINCT res.rid  from iptv_user_play_history iuc,iptv_res res where  iuc.create_time BETWEEN ? and ? and iuc.rid=res.rid and res.type in('song','mv')";
        return this.jdbcTemplate.queryForList(sql,new Object[]{from,to},Long.class);
    }
    public List<Long> findRidByUserCollect(String from,String to){
        String sql="select DISTINCT res.rid  from iptv_user_collect iuc,iptv_res res where  iuc.create_time BETWEEN ? and ? and iuc.rid=res.rid and res.type in('song','mv')";
        return this.jdbcTemplate.queryForList(sql,new Object[]{from,to},Long.class);
    }
    public List<Long> findRidByFree(){
        String sql="select rid  from iptv_res where free=1 and type in ('song','mv')";
        return this.jdbcTemplate.queryForList(sql,Long.class);
    }
	
}
