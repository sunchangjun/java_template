package hk.reco.music.iptv.common.service.stb;

import hk.reco.music.iptv.common.domain.IptvPageResult;
import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import hk.reco.music.iptv.common.exception.IptvBusinessException;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class IptvStbTestService extends IptvStbAbstract {

	public IptvStbTestService() {
		super(true);
	}

	@Override
	public IptvResVer findSingerByRid(long rid)  throws IptvBusinessException{
		return super.findResByRid(IptvObjectEnum.singer, rid);
	}
	
	@Override
	public IptvResVer findMvByRid(long rid)  throws IptvBusinessException{
		return super.findResByRid(IptvObjectEnum.mv, rid);
	}
	
	@Override
	public IptvPageResult findSingerContentList(long prid, int offset, int size) {
		return this.iptvResVerService.findListExcludeGlobalDisablePageable(IptvObjectEnum.singer, IptvObjectEnum.song, prid, null, offset, size, test);
	}
	
	@Override
	public IptvPageResult findSingerSongAndMvList(long prid, int offset, int size) {
		return this.iptvResVerService.findListExcludeGlobalDisablePageable(IptvObjectEnum.singer,null,prid,null,offset,size,test);
	}
	
	@Override
	public IptvPageResult findDissContentList(long prid, int offset, int size) {
		return this.iptvResVerService.findListExcludeGlobalDisablePageable(IptvObjectEnum.diss, null, prid, null,offset, size, test);
	}
	
	@Override
	public IptvResVer findSongByRid(long rid)  throws IptvBusinessException{
		return super.findResByRid(IptvObjectEnum.song, rid);
	}
	
	@Override
	public List<IptvResVer> findLayoutByRid(long prid) {
		return super.findLayoutByRidImpl(prid);
	}
	
	@Override
	public List<IptvResVer> findLayoutList() throws IptvBusinessException{
		return findLayoutListImpl();
	}

	@Override
	public List<IptvResVer> findListByTypeAndChildTypeImpl(IptvObjectEnum type, IptvObjectEnum childtype) {
		return super.iptvResVerService.findListByTypeAndChildType(type,childtype,test);
	}

	@Override
	public IptvPageResult findCategoryContentList(long prid, String pinyin, int offset, int size) {
		return this.iptvResVerService.findListExcludeGlobalDisablePageable(IptvObjectEnum.cate, null, prid, pinyin, offset, size, test);
	}

	@Override
	public List<IptvResVer> findListByTypeAndChildType2Impl(IptvObjectEnum type, IptvObjectEnum childtype) {
		return super.iptvResVerService.findListByTypeAndChildType2(type, childtype, test);
	}
	
	@Override
	public IptvPageResult findTopContentList(long prid, int offset, int size) {
		return this.iptvResVerService.findListExcludeGlobalDisablePageable(IptvObjectEnum.top, null, prid, null,offset, size, test);
	}

	@Override
	public IptvPageResult findColumnContentList(long prid, int offset, int size) {
		return this.iptvResVerService.findListExcludeGlobalDisablePageable(IptvObjectEnum.column, null, prid, null, offset, size, test);
	}

	@Override
	public IptvPageResult findSearchList(IptvObjectEnum type, String pinyin, Integer offset, Integer size) {
		return super.findSearchListImpl(type, pinyin, offset, size);
	}

	@Override
	public List<IptvResVer> findRecoList(IptvObjectEnum type, int size) {
		return super.findRecoListImpl(type, size);
	}

	@Override
	public List<IptvResVer> findUserCollectList(String userid) {
		return super.findUserCollectListImpl(userid);
	}

	@Override
	public void addUserCollect(String mac, String userid, long rid)  throws IptvBusinessException{
		super.addUserCollectImpl(mac, userid, rid);
	}

	@Override
	public void delUserCollect(String userid, long rid) throws IptvBusinessException {
		super.delUserCollectImpl(userid, rid);
	}

	@Override
	public List<IptvResVer> findUserPlayHistoryList(String userid) {
		return super.findUserPlayHistoryListImpl(userid);
	}

	@Override
	public void addUserPlayHistory(String mac, String userid, long rid) throws IptvBusinessException {
		super.addUserPlayHistoryImpl(mac, userid, rid);
	}

	@Override
	public void delUserPlayHistory(String mac, String userid, long rid) throws IptvBusinessException {
		super.delUserPlayHistoryImpl(mac, userid, rid);
	}

	@Override
	public IptvPageResult findFreeResImpl(IptvObjectEnum type, int offset, int size) {
		return super.findFreeRes(type, offset, size);
	}

	@Override
	public IptvResVer findHtmlThemeByRidImpl(long rid) throws IptvBusinessException {
		return super.findHtmlThemeByRid(rid);
	}
	
	@Override
	public IptvPageResult findHtmlThemeListImpl(IptvObjectEnum type, int offset, int size) throws IptvBusinessException {
		return super.findHtmlThemeList(type, offset, size);
	}

}
