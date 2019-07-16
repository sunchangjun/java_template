package hk.reco.music.iptv.common.utils;



import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import hk.reco.music.iptv.common.domain.EasyUiBaseQuery;
import hk.reco.music.iptv.common.domain.EasyUiListResult;


public class BaseDaoSupport extends SqlSessionDaoSupport {
	
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Object object) {
		return (T) this.getSqlSession().selectOne(key, object);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) this.getSqlSession().selectOne(key);
	}
	
	public <T> List<T> getList(String key, Object object) {
		return this.getSqlSession().selectList(key, object);
	}


	/**
	 * 分页查询列表数据
	 * @param key 分页查询的key，对于统计数量的key，会自动在key后加上'_count'，在写mybaits子句的时候自己加上即可
	 * @param query 查询条件实体
	 * @return
	 */
	public <T> EasyUiListResult<T> getListPageSupportByWeb(String key, EasyUiBaseQuery  query) {
		try {
			//查询总数
			Integer totalRecord = this.get(key + "_count", query);
			if (totalRecord == null || totalRecord.intValue() <= 0) {
				return new EasyUiListResult<>();
			}
			//查询数据
			List<T> datas = this.getList(key,query);
			return new EasyUiListResult<T>(totalRecord,datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
