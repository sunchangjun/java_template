package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.domain.IptvRes;
import hk.reco.music.iptv.common.domain.IptvResVer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcTemplateService {
	
	@Autowired
	@Qualifier("iptvJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	
	public List<IptvRes> findAllRes(){
		String sql = "select * from iptv_res";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IptvRes.class));
	}
	
	public List<IptvResVer> findAllResVer(){
		String sql = "select * from iptv_res_ver";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IptvResVer.class));
	}
	
}
