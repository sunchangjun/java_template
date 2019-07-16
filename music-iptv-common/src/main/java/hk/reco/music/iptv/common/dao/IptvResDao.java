package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.IptvRes;
import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IptvResDao {

	public int insert(IptvRes o);// 最简单的创建,仅是占行与类型的保存

	/**
	 * 为res标记版本
	 * @param rid
	 * @param prod_vid 生产环境版本,为null时不更新
	 * @param test_vid 测试环境版本,为null时不更新
	 */
	public void markVersion(@Param("rid") Long rid, @Param("prod_vid") Long prod_vid, @Param("test_vid") Long test_vid);

	public IptvRes findByRid(@Param("rid") Long rid);

	public IptvRes findByResId(@Param("res_id") Long res_id);

	public IptvRes findByCateIds(@Param("cate_ids") String cate_ids);

	public void consoleDeleteByRid(Long rid);

	public void consoleUpdateResByrid(IptvResVer ver);

	public void consoleUpdateResFreeStatus(@Param("rid")Long rid,@Param("free")Integer free);

	// import
	public List<IptvRes> findAllByResidNotNull();
	public void updateMefiaInfo(@Param("rid")Long rid, @Param("iptv_code1")String iptv_code1,@Param("media_definition")String media_definition,@Param("media_lyric")String media_lyric);
	public void updateMvid(@Param("rid")long rid, @Param("song_mv_id")long song_mv_id);
	public List<IptvRes> findByType(@Param("type")IptvObjectEnum type);
	
}