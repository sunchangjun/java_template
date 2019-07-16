package hk.reco.music.iptv.common.rainbow.jsyd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RbJsydService {

	//@Autowired
	//@Qualifier("wthxJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	public List<DailyFlowClear24> findDailyFlow24() {
		String sql = "SELECT id_user,createtime FROM daily_flow_clear24 where createtime between '2019-01-01 00:00:00' and '2019-04-17 23:59:59'";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DailyFlowClear24.class));
	}
	
//	public List<QmSinger> findSomeSinger(int offset, int size) {
//		String sql = "select * from qm_singer limit ?,?";
//		return this.jdbcTemplate.query(sql, new Object[] { offset, size }, new BeanPropertyRowMapper<>(QmSinger.class));
//	}
//
//	public List<QmDiss> find100Diss() {
//		String sql = "select * from qm_diss limit 100";
//		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QmDiss.class));
//	}
//
//	public List<QmSong> findSongByDissid(long dissid) {
//		String sql = "SELECT * FROM qm_song s,qm_diss_song_ref r WHERE r.diss_id=? AND r.song_id=s.song_id";
//		return this.jdbcTemplate.query(sql, new Object[] { dissid }, new BeanPropertyRowMapper<>(QmSong.class));
//	}
//
//	public QmAlbum findAlbumById(long album_id) {
//		String sql = "select * from qm_album where album_id=?";
//		QmAlbum album = null;
//		try {
//			album = this.jdbcTemplate.queryForObject(sql, new Object[] { album_id }, new BeanPropertyRowMapper<>(QmAlbum.class));
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
//		return album;
//	}

	

}
