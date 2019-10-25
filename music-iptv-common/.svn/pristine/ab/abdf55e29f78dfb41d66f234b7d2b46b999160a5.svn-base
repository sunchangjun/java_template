package hk.reco.music.iptv.common.jdbc;

import hk.reco.music.iptv.common.domain.IptvRes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class IptvJdbcService {

	@Autowired
	@Qualifier("iptvJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	public List<IptvRes> findAllSong(){
		String sql = "select * from iptv_res where type='song'";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IptvRes.class));
	}
	
	public List<IptvRes> findAllMv(){
		String sql = "select * from iptv_res where type='mv'";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IptvRes.class));
	}
	
	public void updateSongLyric(String lyric, long rid){
		String sql = "UPDATE iptv_res r SET r.media_lyric=? WHERE r.rid=?";
		this.jdbcTemplate.update(sql, new Object[]{lyric,rid});
	}
	
	public void updateResPlaytime(long rid, int playtime){
		String sql = "UPDATE iptv_res r SET r.play_time=? WHERE r.rid=?";
		this.jdbcTemplate.update(sql, new Object[]{playtime,rid});
	}
	
	public void findVer() {
		String sql = "SELECT VERSION() AS ver";
		try {
			String ver = this.jdbcTemplate.queryForObject(sql, String.class);
			System.out.println(ver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
