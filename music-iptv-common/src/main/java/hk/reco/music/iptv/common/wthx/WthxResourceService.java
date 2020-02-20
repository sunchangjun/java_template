package hk.reco.music.iptv.common.wthx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName WthxResourceService
 * @Description TODO
 * @Author wangpq
 * @Date 2019/5/17 12:46
 * @Version 1.0
 */
@Service
public class WthxResourceService {

    @Autowired
    @Qualifier("wthxJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    private static final String FIND_TOP_BY_ID = "select * from wthx_top where id=?";

    private static final String FIND_MEDIA_BY_TOPID = "SELECT m.* from wthx_top_res_ref as f,wthx_media as m where f.res_id = m.res_id and f.top_id =?";

    private static final String FIND_WTHXMEDIA_BY_RESID = "select * from wthx_media where res_id=?";

    private static final String FIND_BY_STATUS = "select * from wthx_media where status = ?";

    private static final String FIND_SUCCESS = "select res_id,res_type from wthx_media where status = 2";

    private static final String FIND_BY_STATUS_AND_TSURL = "select * from wthx_media where status = 2 and ts_url is not null ";

    private static final String FIND_WTHXMEDIA_BY_MUSICID_TYPE = "select * from wthx_media where media_id=? and res_type=?";

    public WthxTop findTopById(long topId) {
        return this.jdbcTemplate.queryForObject(FIND_TOP_BY_ID, new Object[]{topId}, new BeanPropertyRowMapper<>(WthxTop.class));
    }

    public List<WthxMedia> findMediaByTopId(long topId) {
        return this.jdbcTemplate.query(FIND_MEDIA_BY_TOPID, new Object[]{topId}, new BeanPropertyRowMapper<>(WthxMedia.class));
    }

    public WthxMedia findWthxMediaByResId(long resId) {
        try {
            return this.jdbcTemplate.queryForObject(FIND_WTHXMEDIA_BY_RESID, new Object[]{resId}, new BeanPropertyRowMapper<>(WthxMedia.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public WthxMedia findWthxMediaByMusicId(long musicId, int resType) {
        try {
            return this.jdbcTemplate.queryForObject(FIND_WTHXMEDIA_BY_MUSICID_TYPE, new Object[]{musicId, resType}, new BeanPropertyRowMapper<>(WthxMedia.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<WthxMedia> findMediaByStatus(int status) {
        return this.jdbcTemplate.query(FIND_BY_STATUS, new Object[]{status}, new BeanPropertyRowMapper<>(WthxMedia.class));
    }

    public List<WthxMedia> findSuccessMedia() {
        return this.jdbcTemplate.query(FIND_SUCCESS, new Object[]{}, new BeanPropertyRowMapper<>(WthxMedia.class));
    }

    public List<WthxMedia> findMediaByStatusAndTsUrl() {
        return this.jdbcTemplate.query(FIND_BY_STATUS_AND_TSURL, new Object[]{}, new BeanPropertyRowMapper<>(WthxMedia.class));
    }
}
