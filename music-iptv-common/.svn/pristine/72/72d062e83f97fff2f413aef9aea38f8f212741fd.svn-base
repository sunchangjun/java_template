package hk.reco.music.iptv.common.dao;

import hk.reco.music.iptv.common.domain.EasyUiBaseQuery;
import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IptvResVerDao {
	
	public List<IptvResVer> findByVids(@Param("vids") List<Long> vids);
	public IptvResVer findByRidOnTest(@Param("rid") Long rid);
	public List<IptvResVer> findAllVerByRid(@Param("rid")Long rid);
	
	public List<Long> findLinkByLinkCrid(@Param("rid") Long rid);//找子为rid的link
	public List<Long> findLinkByLinkPrid(@Param("rid") Long rid);//找父为rid的link
	public void updateTwoPoster(@Param("rid") Long rid, @Param("sposter")String sposter,@Param("bposter")String bposter);
	
	/////console
	public int createVersion(IptvResVer ver);
	public int updateResVer(IptvResVer ver);
	public Long findExistResLinkRid(@Param("prid")Long prid, @Param("crid")Long crid);//检测link是否存在,在此只取一个
	
	public IptvResVer consoleBaseResVerByVid(@Param("vid") Long vid);//查询res及ver基本数据,不查singer及其他关联
	public IptvResVer consoleBaseResVerByRid(@Param("rid")Long rid);//查询res及ver基本数据,不查singer及其他关联
	
	public List<IptvResVer> consoleResListByType(@Param("query")EasyUiBaseQuery query, @Param("offset")int offset, @Param("size")int size);
	public int consoleResListByTypeCount(@Param("query") EasyUiBaseQuery query);
	
	//查出资源的版本信息(包括生产与测试版)
	public List<IptvResVer> consoleAllResBriefVerInfo(@Param("rid")Long rid);
	public void consoleResVersionStatuschange(@Param("vid")String versionId);
	public void consoleResChangeGlobalStatus(@Param("rid")Long rid);
	public List<IptvResVer> consolefindLinkBylinkPrid(Long rid);
	public void consoleDeleteByVid(Long vid);
	public List<IptvResVer> consoleLayout();//test环境查类型type的list,version_disable的也显示
	public List<IptvResVer> consoleResListLayoutTree(@Param("prid")long prid);//以test连接数据,但是version_disable=1的test数据不过滤
	
	///////////stb
	public IptvResVer findResByRid(@Param("type")IptvObjectEnum type, @Param("rid")Long rid, @Param("test")boolean test);//TODO 改完后再调整
	
	/**
	 * 非规则布局下获取资源列表
	 * 此方法res和res-singer下线(global_disable)也会显示
	 * @param prid
	 * @param test
	 * @return
	 */
	public List<IptvResVer> findResListIncludeGlobalDisable(@Param("prid")long prid, @Param("test")boolean test);

	//资源列表
	public List<IptvResVer> findResListPageable(@Param("type")IptvObjectEnum type, @Param("listType")IptvObjectEnum listType, @Param("prid")long prid, @Param("keyword")String keyword, 
			@Param("offset")int offset, @Param("size")int size, @Param("test")boolean test);
	public int findResListPageableCount(@Param("type")IptvObjectEnum type, @Param("listType")IptvObjectEnum listType, @Param("prid")long prid, @Param("keyword")String keyword, 
			@Param("test")boolean test);
	
	public List<IptvResVer> findResListByType(@Param("type")IptvObjectEnum type, @Param("offset")int offset, @Param("size")int size, @Param("test")boolean test);
	public int findResListByTypeCount(@Param("type")IptvObjectEnum type, @Param("test")boolean test);
	
	public List<IptvResVer> findFreeResListByType(@Param("type")IptvObjectEnum type, @Param("offset")int offset, @Param("size")int size, @Param("test")boolean test);
	public int findFreeResListByTypeCount(@Param("type")IptvObjectEnum type, @Param("test")boolean test);
	
	//根据类型和子类型查询,通常用于category
	public List<IptvResVer> findResListByTypeAndChildType(@Param("type")IptvObjectEnum type, @Param("child_type")IptvObjectEnum child_type, @Param("test")boolean test);
	
	//搜索接口
	public List<IptvResVer> searchResByKeyword(@Param("type")IptvObjectEnum type, @Param("pinyin")String pinyin, 
			@Param("offset")Integer offset, @Param("size")Integer size, @Param("test")boolean test);
	public int searchResByKeywordCount(@Param("type")IptvObjectEnum type, @Param("pinyin")String pinyin, @Param("test")boolean test);
	
	public List<Long> randomVid(@Param("type")IptvObjectEnum type, @Param("notVids")List<Long> notVids, @Param("size")int size, @Param("test")boolean test);
	
	public List<IptvResVer> findRecoVerByVids(@Param("vids")List<Long> vids, @Param("test")boolean test);
	
}
