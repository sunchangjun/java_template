package hk.reco.music.iptv.common.wthx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QmResourceService {

    @Autowired
    @Qualifier("wthxJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    public QmRes findByResid(long res_id) {
        String sql = "SELECT r.res_id,r.res_type,r.res_name,GROUP_CONCAT(t.tag_name) AS tags FROM qm_res r LEFT JOIN qm_tags AS t ON FIND_IN_SET(CONCAT('t', t.tag_id),r.tags) WHERE r.res_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{res_id}, new BeanPropertyRowMapper<>(QmRes.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmSong findSongByid(long song_id) {
        String sql = "select *  from qm_song where song_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{song_id}, new BeanPropertyRowMapper<>(QmSong.class));
        } catch (Exception e) {
            return null;
        }
    }

    public List<QmSinger> findAllSinger() {
        String sql = "select * from qm_singer limit 5000";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QmSinger.class));
    }

    public List<QmSinger> findSomeSinger(int offset, int size) {
        String sql = "select * from qm_singer limit ?,?";
        return this.jdbcTemplate.query(sql, new Object[]{offset, size}, new BeanPropertyRowMapper<>(QmSinger.class));
    }

    public List<QmDiss> find100Diss() {
        String sql = "select * from qm_diss limit 100";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QmDiss.class));
    }

    public List<QmSong> findSongByDissid(long dissid) {
        String sql = "SELECT * FROM qm_song s,qm_diss_song_ref r WHERE r.diss_id=? AND r.song_id=s.song_id";
        return this.jdbcTemplate.query(sql, new Object[]{dissid}, new BeanPropertyRowMapper<>(QmSong.class));
    }

    public QmAlbum findAlbumById(long album_id) {
        String sql = "select * from qm_album where album_id=?";
        QmAlbum album = null;
        try {
            album = this.jdbcTemplate.queryForObject(sql, new Object[]{album_id}, new BeanPropertyRowMapper<>(QmAlbum.class));
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return album;
    }

    public QmSinger findSingerByid(long singer_id) {
        String sql = "select * from qm_singer where singer_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{singer_id}, new BeanPropertyRowMapper<>(QmSinger.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmSinger findSingerByResid(long res_id) {
        String sql = "SELECT s.*,r.tags FROM qm_res r,qm_singer s WHERE s.res_id=r.res_id AND r.res_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{res_id}, new BeanPropertyRowMapper<>(QmSinger.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmSong findSongByResid(long res_id) {
        String sql = "select * from qm_song where res_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{res_id}, new BeanPropertyRowMapper<>(QmSong.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmDiss findDissByResId(long res_id) {
        String sql = "select d.*,r.tags from qm_diss as d,qm_res as r where d.res_id = r.res_id and d.res_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{res_id}, new BeanPropertyRowMapper<>(QmDiss.class));
        } catch (Exception e) {
            return null;
        }
    }

    public List<QmDiss> findDissByTags(String ttag, int offset, int limit) {
        String sql = "SELECT d.*,r.tags FROM qm_diss d,qm_res r WHERE d.res_id=r.res_id AND r.tags LIKE concat('%',?,'%') LIMIT ?,?";
        return this.jdbcTemplate.query(sql, new Object[]{ttag, offset, limit}, new BeanPropertyRowMapper<>(QmDiss.class));
    }

    public QmMv findMvByResid(long res_id) {
        String sql = "select m.*,r.tags from qm_mv as m,qm_res as r where m.res_id = r.res_id and m.res_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{res_id}, new BeanPropertyRowMapper<>(QmMv.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmMv findMvByTitleAndSinger(String title, String singer) {
        String sql = "SELECT m.mv_id,m.res_id,m.pic_url FROM qm_mv m WHERE m.mv_title=? AND m.singer_name=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{title, singer}, new BeanPropertyRowMapper<>(QmMv.class));
        } catch (Exception e) {
            List<QmMv> mvs = this.jdbcTemplate.query(sql, new Object[]{title, singer}, new BeanPropertyRowMapper<>(QmMv.class));
            if (mvs.size() != 0) {
                return mvs.get(0);
            } else {
                return null;
            }
        }
    }

    public QmMv findMvByMvid(long mv_id) {
        String sql = "SELECT m.*,tags FROM qm_mv m,qm_res r WHERE m.res_id=r.res_id AND m.mv_id =?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{mv_id}, new BeanPropertyRowMapper<>(QmMv.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmTags findTagById(long tag_id) {
        String sql = "SELECT * FROM qm_tags t WHERE t.tag_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{tag_id}, new BeanPropertyRowMapper<>(QmTags.class));
        } catch (Exception e) {
            return null;
        }
    }

    public List<QmTop> findAllTop() {
        String sql = "SELECT * FROM qm_top";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QmTop.class));
    }

    public List<QmMvTop> findAllMvTop() {
        String sql = "SELECT * FROM qm_mv_top";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QmMvTop.class));
    }

    public QmTop findTopByTopId(long top_id) {
        String sql = "SELECT * FROM qm_top t where t.top_id = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{top_id}, new BeanPropertyRowMapper<>(QmTop.class));
    }

    public List<QmMv> findMvByTag(String tag, int off, int size) {
        String sql = "SELECT r.res_id,m.* FROM qm_res r,qm_mv m WHERE m.res_id=r.res_id AND r.tags LIKE concat('%',?,'%') LIMIT ?,?";
        return this.jdbcTemplate.query(sql, new Object[]{tag, off, size}, new BeanPropertyRowMapper<>(QmMv.class));
    }

    public List<QmSong> findSongByTopid(long top_id) {
        String sql = "SELECT s.* FROM qm_top t,qm_top_song_ref r,qm_song s WHERE t.id=r.top_id AND s.song_id=r.song_id AND t.id=?";
        return this.jdbcTemplate.query(sql, new Object[]{top_id}, new BeanPropertyRowMapper<>(QmSong.class));
    }

    public List<QmMv> findMvByTopid(long top_id) {
        String sql = "SELECT r.sort,m.* FROM qm_mv_top t,qm_mv_top_ref r,qm_mv m WHERE t.id=r.top_id AND m.mv_id=r.mv_id AND t.id=?";
        return this.jdbcTemplate.query(sql, new Object[]{top_id}, new BeanPropertyRowMapper<>(QmMv.class));
    }

    public QmTransCodeVer findTransCodeResIdAndComposer(long resId, int resType, int composer) {
        try {
            String sql = "select v.*,r.src_def from qm_transcode_ver as v,qm_transcode_res as r where r.res_id = ? and r.res_type = ? and v.parent_id = r.id and r.work_done = 1 and r.composer = ?";
            return this.jdbcTemplate.queryForObject(sql, new Object[]{resId, resType, composer}, new BeanPropertyRowMapper<>(QmTransCodeVer.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public QmTransCodeVer findTransCodeIptvResIdAndComposer(long resId, int resType, int composer) {
        try {
            String sql = "select v.*,r.src_def from qm_transcode_ver_iptv as v,qm_transcode_res_iptv as r where r.res_id = ? and r.res_type = ? and v.parent_id = r.id and r.work_done = 1 and r.composer = ?";
            return this.jdbcTemplate.queryForObject(sql, new Object[]{resId, resType, composer}, new BeanPropertyRowMapper<>(QmTransCodeVer.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public QmMv findInjectMvById(Long mvId) {
        try {
            String sql = "select a.*,b.res_name_py,b.res_singer_py,c.singer_pic_local,c.country,GROUP_CONCAT(d.tag_name) AS tags" +
                    " from qm_mv a inner join qm_res b on  a.res_id = b.res_id " +
                    " left join qm_singer c on a.singer_id = c.singer_id left join qm_tags AS d ON find_in_set(CONCAT('t', d.tag_id), b.tags)" +
                    " where mv_id = ?  and  playable != 0 and c.disabled = 0 ";
            return this.jdbcTemplate.queryForObject(sql, new Object[]{mvId}, new BeanPropertyRowMapper<>(QmMv.class));
        } catch (Exception e) {
            return null;
        }
    }

    public QmSong findInjectSongById(Long songId) {
        try {
            String sql = "select a.*,b.res_name_py,b.res_singer_py,c.singer_pic_local,c.country," +
                    " d.album_mid,d.album_pic_local,GROUP_CONCAT(e.tag_name) AS tags from qm_song a " +
                    " inner join qm_res b on  a.res_id = b.res_id left join qm_singer c on a.singer_id = c.singer_id " +
                    " left join qm_album d on a.album_id = d.album_id left join qm_tags AS e ON FIND_IN_SET(CONCAT('t', e.tag_id), b.tags)" +
                    " where a.song_id = ? and c.disabled = 0 ";
            return this.jdbcTemplate.queryForObject(sql, new Object[]{songId}, new BeanPropertyRowMapper<>(QmSong.class));
        } catch (Exception e) {
            return null;
        }
    }
}
