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

    private static final String FIND_ALL = "select * from wthx_media";

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

    public List<WthxMedia> findMediaAll() {
        return this.jdbcTemplate.query(FIND_ALL, new Object[]{}, new BeanPropertyRowMapper<>(WthxMedia.class));
    }
}
