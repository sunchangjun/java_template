package hk.reco.music.iptv.common.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hk.reco.music.iptv.common.annotation.LogDetail;
import hk.reco.music.iptv.common.dao.IptvResDailyPlayDao;
import hk.reco.music.iptv.common.dao.IptvResDao;
import hk.reco.music.iptv.common.dao.IptvResVerDao;
import hk.reco.music.iptv.common.dao.IptvStatsUserDetailDao;
import hk.reco.music.iptv.common.dao.IptvUserCollectDao;
import hk.reco.music.iptv.common.dao.IptvUserDao;
import hk.reco.music.iptv.common.dao.IptvUserPlayHistoryDao;
import hk.reco.music.iptv.common.dao.IptvWhiteListUserDao;
import hk.reco.music.iptv.common.domain.IptvPage;
import hk.reco.music.iptv.common.domain.IptvPageResult;
import hk.reco.music.iptv.common.domain.IptvRes;
import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.domain.IptvUser;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import hk.reco.music.iptv.common.enums.IptvPlatform;
import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.exception.IptvError;
import hk.reco.music.iptv.common.service.stb.IptvStbAbstract;
import hk.reco.music.iptv.common.stats.IptvSpecialRid;
import hk.reco.music.iptv.common.stats.IptvStatsAction;
import hk.reco.music.iptv.common.stats.IptvStatsLogger;
import hk.reco.music.iptv.common.utils.IptvFileUtils;
import hk.reco.music.iptv.common.utils.LogHelper;
/**
 * 整合过后服务,适合web与apk,适合test和prod环境
 * @author zhangsl
 * @date 2019年5月27日
 */
@Service
public class IptvStbService {
	
	@Autowired
	protected IptvCacheService iptvCacheService;
	@Autowired
	protected List<IptvStbAbstract> iptvStbAbstracts;
	@Autowired
	protected IptvResVerService iptvResVerService;
	@Autowired
	protected IptvResDao iptvResDao;
	@Autowired
	protected IptvResVerDao iptvResVerDao;
	@Autowired
	protected IptvStatsUserDetailDao iptvStatsUserLoginDao;
	@Autowired
	protected IptvResDailyPlayDao iptvResDailyPlayDao;
	@Autowired
	protected IptvUserCollectDao iptvUserCollectDao;
	@Autowired
	protected IptvUserDao iptvUserDao;
	@Autowired
	protected IptvUserPlayHistoryDao iptvUserPlayHistoryDao;
    @Autowired
    protected IptvWhiteListUserDao iptvWhiteListUserDao;
    @Value("${partner:default}")
    private String partner;
	
	private static final Logger log = LoggerFactory.getLogger(IptvStbService.class);
	
	protected void log(String platform, String ip, String mac, String userId, Long prid, Long rid, Long extrid, 
			String pinyin, IptvObjectEnum type, Integer duration, String method, long now, boolean test, String path) throws Exception{
		platform = (platform==null)?IptvPlatform.apk.name():platform;
		String data = IptvStatsLogger.data(IptvStatsAction.reqs, ip, mac, userId, prid, rid, extrid, pinyin, type, duration, method, platform, now, test, path);
		log.info(data);
	}
	
	//取所有的顶层频道
	@LogDetail(method = "get_layouts_impl", rid = IptvSpecialRid.root + "")
	public List<IptvResVer> get_layouts_impl(String platform, String ip, String mac, String userId, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, null, IptvSpecialRid.root, null, null, null, null, "get_layouts_impl", now, test, path);
		List<IptvResVer> vers = this.iptvCacheService.loadCategoryList(IptvObjectEnum.layout, null, test);
		return vers;
	}
	
	//根据顶层布局rid取内容
	@LogDetail(method = "get_layout_by_rid_impl")
	public List<IptvResVer> get_layout_by_rid_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_layout_by_rid_impl", now, test, path);
		List<IptvResVer> vers = this.iptvCacheService.loadResChilds(rid, IptvObjectEnum.layout, test, true);
		for(IptvResVer ver : vers){
			boolean regular = (ver.getLayout_regular()==null|| ver.getLayout_regular())?true:false;
			List<IptvResVer> subs = this.iptvCacheService.loadResChilds(ver.getRid(), ver.getType(), test, regular);
			ver.setList(subs);
		}
		return vers;
	}
	
	//取歌手分类
	@LogDetail(method = "get_singer_category_impl")
	public List<IptvResVer> get_singer_category_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_singer_category_impl", now, test, path);
		return this.iptvCacheService.loadCategoryList(IptvObjectEnum.cate, IptvObjectEnum.singer, test);
	}

	//取分类下的资源,类型可能是[singer|diss|mv]等
	@LogDetail(method = "get_cate_content_list_impl")
	public IptvPageResult get_cate_content_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, String pinyin, IptvPage page,
			boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		int offset = page.getOffset();
		int size = page.getSize();
		this.log(platform, ip, mac, userId, prid, rid, null, pinyin, null, null, "get_cate_content_list_impl", now, test, path);
		return this.iptvCacheService.loadResChilds(rid, pinyin, IptvObjectEnum.cate, null, offset, size, test);
	}

	//根据歌手rid取下面的歌曲
	@LogDetail(method = "get_singer_song_list_impl")
	public IptvPageResult get_singer_song_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvPage page, boolean test, String path)
			throws Exception {
		long now = System.currentTimeMillis();
		int offset = page.getOffset();
		int size = page.getSize();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_singer_song_list_impl", now, test, path);
		return this.iptvCacheService.loadResChilds(rid, null, IptvObjectEnum.singer, IptvObjectEnum.song, offset, size, test);
	}
	
	//根据歌手rid取下面的所有资源,包括mv|song
	@LogDetail(method = "get_singer_song_and_mv_list_impl")
	public IptvPageResult get_singer_song_and_mv_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvPage page, boolean test, String path)
			throws Exception {
		long now = System.currentTimeMillis();
		int offset = page.getOffset();
		int size = page.getSize();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_singer_song_and_mv_list_impl", now, test, path);
		return this.iptvCacheService.loadResChilds(rid, null, IptvObjectEnum.singer, null, offset, size, test);
	}
	
	//取歌单分类
	@LogDetail(method = "get_diss_category_impl")
	public List<IptvResVer> get_diss_category_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_diss_category_impl", now, test, path);
		return this.iptvCacheService.loadCategoryList(IptvObjectEnum.cate, IptvObjectEnum.diss, test);
	}

	//取歌单下的song
	@LogDetail(method = "get_diss_song_list_impl")
	public IptvPageResult get_diss_song_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvPage page, boolean test, String path)
			throws Exception {
		long now = System.currentTimeMillis();
		int offset = page.getOffset();
		int size = page.getSize();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_diss_song_list_impl", now, test, path);
		IptvObjectEnum child = (platform==null || platform.equals("apk"))?IptvObjectEnum.song:null;
		return this.iptvCacheService.loadResChilds(rid, null, IptvObjectEnum.diss, child, offset, size, test);
	}
	
	//歌曲详情
	@LogDetail(method = "get_song_info_impl")
	public IptvResVer get_song_info_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_song_info_impl", now, test, path);
		return this.iptvCacheService.loadResByRid(rid, test);
	}

	//取榜单分类,共计两层
	@LogDetail(method = "get_top_category_impl")
	public List<IptvResVer> get_top_category_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_top_category_impl", now, test, path);
		List<IptvResVer> cates = this.iptvCacheService.loadCategoryList(IptvObjectEnum.cate, IptvObjectEnum.top, test);
		for(IptvResVer cate : cates){//再查分类下的二级分类
			List<IptvResVer> tops = this.iptvCacheService.loadResChilds(cate.getRid(), IptvObjectEnum.cate, test, true);
			cate.setList(tops);
		}
		return cates;
	}

	//由榜单rid取下面的内容(song|mv)
	@LogDetail(method = "get_top_content_list_impl")
	public IptvPageResult get_top_content_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvPage page, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		int offset = page.getOffset();
		int size = page.getSize();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_top_content_list_impl", now, test, path);
		return this.iptvCacheService.loadResChilds(rid, null, IptvObjectEnum.top, null, offset, size, test);
	}

	//由栏目rid取下面的内容(song|mv|diss|column|singer)
	@LogDetail(method = "get_column_content_list_impl")
	public IptvPageResult get_column_content_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvPage page, boolean test, String path)
			throws Exception {
		long now = System.currentTimeMillis();
		int offset = page.getOffset();
		int size = page.getSize();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_column_content_list_impl", now, test, path);
		return this.iptvCacheService.loadResChilds(rid, null, IptvObjectEnum.column, null, offset, size, test);
	}

    //自动播放rid取下面的内容(mv)
    @LogDetail(method = "get_auto_play_list_impl")
    public IptvPageResult get_auto_play_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvPage page, boolean test, String path)
            throws Exception {
        long now = System.currentTimeMillis();
        int offset = page.getOffset();
        int size = page.getSize();
        this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_auto_play_list_impl", now, test, path);
        return this.iptvCacheService.loadResChilds(rid, null, IptvObjectEnum.auto_play_diss, null, offset, size, test);
    }

	//由mv的rid取详情
	@LogDetail(method = "get_mv_info_impl")
	public IptvResVer get_mv_info_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "get_mv_info_impl", now, test, path);
		return this.iptvCacheService.loadResByRid(rid, test);
	}
	
	//取mv分类
	@LogDetail(method = "get_mv_category_impl")
	public List<IptvResVer> get_mv_category_impl(String platform, String ip, String mac, String userId, Long prid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, null, null, null, null, null, "get_mv_category_impl", now, test, path);
		return this.iptvCacheService.loadCategoryList(IptvObjectEnum.cate, IptvObjectEnum.mv, test);
	}
	
	//rid查找html5专题
	@LogDetail(method = "find_html_theme")
	public IptvResVer find_html_theme_by_rid(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		platform = (platform==null)?IptvPlatform.apk.name():platform;
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "find_html_theme", now, test, path);
		IptvResVer theme = this.iptvCacheService.loadResByRid(rid, test);
		IptvFileUtils.toHttp(theme);//处理专题的地址
		List<IptvResVer> vers = this.iptvCacheService.loadResChilds(rid, theme.getType(), test, true);
		theme.setList(vers);
		return theme;
	}
	
	//分页查找html5专题
	@LogDetail(method = "find_html_theme_list")
	public IptvPageResult find_html_theme_list(String platform, String ip, String mac, String userId, Long prid, Long rid, int offset, int size, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		platform = (platform==null)?IptvPlatform.apk.name():platform;
		this.log(platform, ip, mac, userId, prid, rid, null, null, null, null, "find_html_theme_list", now, test, path);
		return this.iptvCacheService.loadTypeList(IptvObjectEnum.theme, offset, size, test);
	}
	
	////////////////////////////////////////以下方法不走缓存////////////////////////
	
	//拼音首字母搜索,无缓存,搜索的结果有限,所以不用count,全查出来取子集即可
	@LogDetail(method = "get_search_list_impl", extrid = IptvSpecialRid.search_by_pinyin + "")
	public IptvPageResult get_search_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, String pinyin, String type, int offset, int size, boolean test, String path)
			throws Exception {
		long now = System.currentTimeMillis();
		IptvObjectEnum v = IptvObjectEnum.getType(type);
		if(v!=null && (v==IptvObjectEnum.song || v==IptvObjectEnum.mv || v==IptvObjectEnum.singer)){
			this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.search_by_pinyin, pinyin, v, null, "get_search_list_impl", now, test, path);
			IptvPageResult result = new IptvPageResult();
			
			List<IptvResVer> vers = this.iptvResVerDao.searchResByKeyword(v, pinyin, test);
			if(offset>=vers.size()){//取不到数据的空集合
				result.setVers(new ArrayList<>());
				result.setTotal(vers.size());
			}else{
				IptvFileUtils.toHttp(vers);
				int max_index = ((offset+size)>vers.size())?vers.size():(offset+size);
				result.setVers(vers.subList(offset, max_index));
				result.setTotal(vers.size());
			}
			return result;
		}else{
			throw new IptvBusinessException(IptvError.SEARCH_TYPE_ERROR);
		}
	}
	
	//搜索页提供一部分推荐内容(type=[song|mv|singer])
	@LogDetail(method = "get_search_reco_list_impl", extrid = IptvSpecialRid.search_recommend + "")
	public List<IptvResVer> get_search_reco_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, String type, Integer size, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		if(size == null){
			size = 20;
		}
		IptvObjectEnum v = IptvObjectEnum.getType(type);
		if(v!=null && (v==IptvObjectEnum.song || v==IptvObjectEnum.mv || v==IptvObjectEnum.singer)){
			this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.search_recommend, null, v, null, "get_search_reco_list_impl", now, test, path);
			//查找思路,查找最近1周的按资源热度排序的结果,如果不够,则random几个出来
			long ts = System.currentTimeMillis();
			Date to = new Date(ts);
			Date from = new Date(ts-6*24*60*60*1000);
			List<Long> hots = this.iptvResDailyPlayDao.findWeekResVid(v, from, to, size, test);//查出最近7天的热度统计,同时考虑到了禁用的情况,需要先联合查出来
			hots.clear();
			if(hots.size()<size){//不够则补齐
				List<Long> others = this.iptvResVerDao.randomVid(v, hots, size-hots.size(), test);
				hots.addAll(others);
			}
			List<IptvResVer> vers = this.iptvResVerDao.findRecoVerByVids(hots, test);
			IptvFileUtils.toHttp(vers);
			return vers;
		}else{
			throw new IptvBusinessException(IptvError.SEARCH_TYPE_ERROR);
		}
	}
	
	//用户取收藏(一次性取完)
	@LogDetail(method = "get_user_collect_list_impl", extrid = IptvSpecialRid.get_user_collect + "")
	public List<IptvResVer> get_user_collect_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.get_user_collect, null, null, null, "get_user_collect_list_impl", now, test, path);
		List<IptvResVer> vers = this.iptvUserCollectDao.findCollectByUserid(userId, test);
		IptvFileUtils.toHttp(vers);
		return vers;
	}

	//用户添加收藏(最多100个,如果超过会顶掉最老的,如果有重复只更新时间)
	@LogDetail(method = "user_collect_add_impl", extrid = IptvSpecialRid.add_user_collect + "")
	public void user_collect_add_impl(String platform, String ip, String mac, String userId, Long prid, long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.add_user_collect, null, null, null, "user_collect_add_impl", now, test, path);
		IptvRes res = this.iptvResDao.findByRid(rid);
		int count = this.iptvUserCollectDao.findCollectCount(userId, res.getType().name(), test);
		if(count>=100){
			throw new IptvBusinessException(IptvError.USER_COLLECT_LIMIT_ERROR);
		}
		IptvObjectEnum type = res.getType();
		if(type==IptvObjectEnum.song || type==IptvObjectEnum.mv || type==IptvObjectEnum.diss || type==IptvObjectEnum.column){
		}else{
			throw new IptvBusinessException(IptvError.USER_COLLECT_TYPE_ERROR);
		}
		try{
			IptvUser user = this.iptvUserDao.findByUserId(userId,test ? 1 : 0);
			if (user == null || StringUtils.isBlank(user.getUser_id())) {
				user = new IptvUser();
				user.setUser_id(userId);
				user.setMac(mac);
				user.setTest(test);
				this.iptvUserDao.ignoreInsertWhenExist(user);//不会报错,如果存在
				LogHelper.printNewUser(partner, mac, userId, test);
			}
			
			this.iptvUserCollectDao.addCollect(userId, rid, new java.util.Date(), test);
		}catch(org.springframework.dao.DataIntegrityViolationException e){
			throw new IptvBusinessException(IptvError.USER_COLLECT_ERROR);
		}
	}
	
	//用户删除收藏
	@LogDetail(method = "user_collect_del_impl", extrid = IptvSpecialRid.del_user_collect + "")
	public void user_collect_del_impl(String platform, String ip, String mac, String userId, Long prid, long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.del_user_collect, null, null, null, "user_collect_add_impl", now, test, path);
		this.iptvUserCollectDao.delCollect(userId, rid, test);
	}

	//用户取播放历史(一次性取完)
	@LogDetail(method = "user_play_history_list_impl", extrid = IptvSpecialRid.get_user_history + "")
	public List<IptvResVer> user_play_history_list_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.get_user_history, null, null, null, "user_play_history_list_impl", now, test, path);
		List<IptvResVer> vers = this.iptvUserPlayHistoryDao.findHistoryByUserid(userId, test);
		IptvFileUtils.toHttp(vers);
		return vers;
	}

	public List<String> getIptvWhiteList(Boolean test){
       List<String> userIds= iptvWhiteListUserDao.findValidUserId(test);
	    return  userIds;
    }

	//用户添加播放历史(type=[song|mv])
	@LogDetail(method = "user_play_history_add_impl", extrid = IptvSpecialRid.add_user_history + "")
	public void user_play_history_add_impl(String platform, String ip, String mac, String userId, Long prid, long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.add_user_history, null, null, null, "user_play_history_add_impl", now, test, path);
		try{
			IptvUser user = this.iptvUserDao.findByUserId(userId,test ? 1 : 0);
			if (user == null || StringUtils.isBlank(user.getUser_id())) {
				user = new IptvUser();
				user.setUser_id(userId);
				user.setMac(mac);
				user.setTest(test);
				this.iptvUserDao.ignoreInsertWhenExist(user);//不会报错,如果存在
				LogHelper.printNewUser(partner, mac, userId, test);
			}
			
			IptvRes res = this.iptvResDao.findByRid(rid);
			List<Long> ids = this.iptvUserPlayHistoryDao.findPlayHistoryIds(userId, res.getType().name(), test);
			if(ids.size()==100){
				this.iptvUserPlayHistoryDao.delPlayHistory(userId, ids.get(99), test);
			}
			this.iptvUserPlayHistoryDao.addPlayHistory(userId, rid, new java.util.Date(), test);
		}catch(org.springframework.dao.DataIntegrityViolationException e){
			throw new IptvBusinessException(IptvError.USER_COLLECT_ERROR);
		}
	}
	
	//用户删除播放历史
	@LogDetail(method = "user_play_history_del_impl", extrid = IptvSpecialRid.del_user_history + "")
	public void user_play_history_del_impl(String platform, String ip, String mac, String userId, Long prid, long rid, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, IptvSpecialRid.del_user_history, null, null, null, "user_play_history_del_impl", now, test, path);
		this.iptvUserPlayHistoryDao.delPlayHistory(userId, rid, test);
	}

	//取免费内容(song|mv), TODO ???关于免费是否取缓存
	@LogDetail(method = "get_free_impl")
	public IptvPageResult get_free_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, IptvObjectEnum type, int offset, int size, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		this.log(platform, ip, mac, userId, prid, rid, null, null, type, null, "get_free_impl", now, test, path);
		IptvPageResult result = new IptvPageResult();
		int total = this.iptvResVerDao.findFreeResListByTypeCount(type, test);
		if(total>0){
			List<IptvResVer> vers = this.iptvResVerDao.findFreeResListByType(type, offset, size, test);
			IptvFileUtils.toHttp(vers);
			result.setVers(vers);
			result.setTotal(total);
		}else{
			result.setVers(new ArrayList<IptvResVer>());
			result.setTotal(0);
		}
		return result;
	}

	//上报播放内容与时长(song|mv),以秒为单位
	@LogDetail(action = IptvStatsAction.play, method = "play_report_impl")
	public void play_report_impl(String platform, String ip, String mac, String userId, Long prid, Long rid, Integer duration, boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		platform = (platform==null)?IptvPlatform.apk.name():platform;
		IptvRes res = this.iptvResDao.findByRid(rid);
		String data = IptvStatsLogger.data(IptvStatsAction.play, ip, mac, userId, prid, rid, Long.valueOf(res.getFree()), null, null, duration, "play_report_impl", platform, now, test, path);
		log.info(data);
		this.iptvResDailyPlayDao.addDailyPlayNum(new java.sql.Date(now), rid);
	}
    //上报订单数据
    @LogDetail(action = IptvStatsAction.order, method = "order_report_impl")
    public void order_report_impl(String platform, String ip, String mac, String userId, Long prid, Long rid,
                                  String order_id,String product_id,String product_price,Integer result_code,String other_desc,
                                  boolean test, String path) throws Exception {
        long now = System.currentTimeMillis();
        platform = (platform==null)?IptvPlatform.apk.name():platform;
        String data = IptvStatsLogger.order(IptvStatsAction.order, ip, mac, userId, prid, rid, order_id, product_id,product_price, result_code,other_desc,"order_report_impl", platform, now, test, path);
        log.info(data);
    }


	
	@LogDetail(method = "remove_all_play_history")
	public void remove_all_play_history(String platform, String ipAddress, String mac, String userId, Long prid, Long rid, Boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		platform = (platform==null)?IptvPlatform.apk.name():platform;
		this.log(platform, ipAddress, mac, userId, prid, rid, null, null, null, null, "remove_all_play_history", now, test, path);
		this.iptvUserPlayHistoryDao.removeAllPlayHistory(userId,test);
	}

	@LogDetail(method = "remove_all_gd_collect")
	public void remove_all_gd_collect(String platform, String ipAddress, String mac, String userId, Long prid, Long rid, Boolean test, String path) throws Exception {
		long now = System.currentTimeMillis();
		platform = (platform==null)?IptvPlatform.apk.name():platform;
		this.log(platform, ipAddress, mac, userId, prid, rid, null, null, null, null, "remove_all_gd_collect", now, test, path);
		List<IptvResVer> collect = iptvUserCollectDao.findCollectByUserid(userId, test);
		if(collect!=null && collect.size()>0){
			for (IptvResVer iptvResVer : collect) {
				if(IptvObjectEnum.diss.equals(iptvResVer.getType())){
					iptvUserCollectDao.delCollect(userId,iptvResVer.getRid(),test);
				}
			}
		}
	}
	
}
