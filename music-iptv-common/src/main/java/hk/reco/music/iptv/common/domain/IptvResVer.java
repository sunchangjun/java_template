package hk.reco.music.iptv.common.domain;

import hk.reco.music.iptv.common.enums.IptvObjectEnum;

import java.util.Date;
import java.util.List;

/**
 * 版本对象
 * @author zhangsl
 * @date 2019年2月22日
 */
public class IptvResVer extends IptvRes implements java.io.Serializable{
	
	private static final long serialVersionUID = 376871226954556004L;
	private Long vid;
	private String name;
	private Long link_prid;
	private Long link_crid;
	private Float sort = 0f;
	private String keyword;
	private String country;
	private Integer sex;
	private String bposter;
	private String sposter;
	private String fposter;
	private Date version_create_time;
	private String layout_start;
	private String layout_end;
	private Integer layout_line;
	private Boolean layout_show_title;
	private Boolean layout_show_child_title;
	private Boolean layout_big;
	private Boolean layout_regular;
	//非本表字段
	private String poster;//定制的海报,这个是link上的海报属性
	private String singer_name;
	private Long singer_rid;
	private String singer_res_ids;
	private List<IptvResVer> list;
	private Long link_vid;
	private Long link_rid;
	private Long link_test_vid;
	private Long link_prod_vid;
	private Boolean link_version_disable;
	private Boolean commit;//树属性,标识是否子类有未提交到生产的情况
	private Integer child_num;//动态查询生成子数量
	private IptvResVer parent;
	private Boolean singer_global_disable;
	private boolean available = true;
	
	public static IptvResVer createSong(String name, String keyword, String sposter, String bposter, String media_definition, 
			String media_lyric, Long res_id, Long music_id, Long mv_id, int play_time, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setKeyword(keyword);
		ver.setType(IptvObjectEnum.song);
		ver.setBposter(bposter);
		ver.setSposter(sposter);
		ver.setMedia_definition(media_definition);
		ver.setMedia_lyric(media_lyric);
		ver.setRes_id(res_id);
		ver.setMusic_id(music_id);
		ver.setSong_mv_id(mv_id);
		ver.setPlay_time(play_time);
		ver.setBatch_num(batch_num);
		ver.setMedia_ok(false);
		return ver;
	}

	public static IptvResVer createMv(String name, String keyword, String sposter, String bposter, Long res_id, Long music_id, Integer play_time, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setKeyword(keyword);
		ver.setType(IptvObjectEnum.mv);
		ver.setBposter(bposter);
		ver.setSposter(sposter);
		ver.setRes_id(res_id);
		ver.setMusic_id(music_id);
		ver.setPlay_time(play_time);
		ver.setBatch_num(batch_num);
		ver.setMedia_ok(false);
		return ver;
	}
	
	public static IptvResVer createMv(String name, String keyword, String sposter, String bposter, Long res_id, Long music_id, Integer play_time, Integer batch_num,String media_definition){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setKeyword(keyword);
		ver.setType(IptvObjectEnum.mv);
		ver.setBposter(bposter);
		ver.setSposter(sposter);
		ver.setRes_id(res_id);
		ver.setMusic_id(music_id);
		ver.setPlay_time(play_time);
		ver.setBatch_num(batch_num);
		ver.setMedia_ok(false);
		ver.setMedia_definition(media_definition);
		return ver;
	}
	
	/**
	 * 初始化一个歌手
	 * @param name
	 * @param sposter
	 * @param bposter
	 * @return
	 */
	public static IptvResVer createSinger(String name, String country, Integer sex, String keyword, String sposter, String bposter, Long res_id, Long music_id, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(IptvObjectEnum.singer);
		ver.setSex(sex);
		ver.setKeyword(keyword);
		ver.setBposter(bposter);
		ver.setSposter(sposter);
		ver.setRes_id(res_id);
		ver.setMusic_id(music_id);
		ver.setCountry(country);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createLink(Long link_prid, Long link_crid, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName("link-"+link_prid+"-"+link_crid);
		ver.setType(IptvObjectEnum.link);
		ver.setLink_prid(link_prid);
		ver.setLink_crid(link_crid);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createLink(Long link_prid, Long link_crid, Float sort, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName("link-"+link_prid+"-"+link_crid);
		ver.setType(IptvObjectEnum.link);
		ver.setLink_prid(link_prid);
		ver.setLink_crid(link_crid);
		ver.setBatch_num(batch_num);
		ver.setSort(sort);
		return ver;
	}
	
	public static IptvResVer createLink(Long link_prid, Long link_crid, Integer batch_num,String layout_start,
			String layout_end,Integer layout_line,Boolean layout_show_title,Boolean layout_show_child_title,Boolean layout_big){
		IptvResVer ver = new IptvResVer();
		ver.setName(createLinkName(link_prid, link_crid));
		ver.setType(IptvObjectEnum.link);
		ver.setLink_prid(link_prid);
		ver.setLink_crid(link_crid);
		ver.setLayout_start(layout_start);
		ver.setLayout_end(layout_end);
		ver.setLayout_line(layout_line);
		ver.setLayout_show_title(layout_show_title);
		ver.setLayout_show_child_title(layout_show_child_title);
		ver.setLayout_big(layout_big);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createDiss(String name, String sposter, String bposter, Long res_id, Long music_id, Long listen_num, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(IptvObjectEnum.diss);
		ver.setChild_type(IptvObjectEnum.song);
		ver.setBposter(bposter);
		ver.setSposter(sposter);
		ver.setRes_id(res_id);
		ver.setMusic_id(music_id);
		ver.setListen_num(listen_num);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createColumn(IptvObjectEnum type, IptvObjectEnum child_type, String name, Integer layout_line, boolean regular, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(type);
		ver.setChild_type(child_type);
		ver.setLayout_line(layout_line);
		ver.setLayout_regular(regular);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createCategory(IptvObjectEnum child_type, String tag_name, String cate_ids, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(tag_name);
		ver.setType(IptvObjectEnum.cate);
		ver.setChild_type(child_type);
		ver.setCate_ids(cate_ids);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createCategory(IptvObjectEnum child_type, String tag_name, String cate_ids, String poster, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(tag_name);
		ver.setType(IptvObjectEnum.cate);
		ver.setChild_type(child_type);
		ver.setCate_ids(cate_ids);
		ver.setBposter(poster);
		ver.setSposter(poster);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createTop(IptvObjectEnum child_type, String name, String poster, String cate_ids, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(IptvObjectEnum.top);
		ver.setChild_type(child_type);
		ver.setCate_ids(cate_ids);
		ver.setBposter(poster);
		ver.setSposter(poster);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createTopCategory(IptvObjectEnum child_type, String name, String poster, String cate_ids, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(IptvObjectEnum.cate);
		ver.setChild_type(child_type);
		ver.setCate_ids(cate_ids);
		ver.setBposter(poster);
		ver.setSposter(poster);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createLayout(String name, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(IptvObjectEnum.layout);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	public static IptvResVer createFunc(IptvObjectEnum child_type, String name, Integer batch_num){
		IptvResVer ver = new IptvResVer();
		ver.setName(name);
		ver.setType(IptvObjectEnum.func);
		ver.setChild_type(child_type);
		ver.setBatch_num(batch_num);
		return ver;
	}
	
	/**
	 * 级联查询子list时,会带出link信息,在此取出来
	 * @param sub
	 * @return
	 */
	public static IptvResVer getLinkInfo(IptvResVer sub){
		IptvResVer vl = new IptvResVer();
		vl.setRid(sub.getLink_rid());
		vl.setVid(sub.getLink_vid());
		vl.setLink_prid(sub.getLink_prid());
		vl.setTest_vid(sub.getLink_test_vid());
		vl.setProd_vid(sub.getLink_prod_vid());
		vl.setType(IptvObjectEnum.link);
		return vl;
	}
	
	public static String createLinkName(Long link_prid, Long link_crid){
		return "link-"+link_prid+"-"+link_crid;
	}
	
	public Long getVid() {
		return vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getSort() {
		return sort;
	}
	public void setSort(Float sort) {
		this.sort = sort;
	}
	public String getBposter() {
		return bposter;
	}
	public void setBposter(String bposter) {
		this.bposter = bposter;
	}
	public String getSposter() {
		return sposter;
	}
	public void setSposter(String sposter) {
		this.sposter = sposter;
	}
	public Date getVersion_create_time() {
		return version_create_time;
	}
	public void setVersion_create_time(Date version_create_time) {
		this.version_create_time = version_create_time;
	}
	public Long getLink_prid() {
		return link_prid;
	}
	public void setLink_prid(Long link_prid) {
		this.link_prid = link_prid;
	}
	public Long getLink_crid() {
		return link_crid;
	}
	public void setLink_crid(Long link_crid) {
		this.link_crid = link_crid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public String getSinger_res_ids() {
		return singer_res_ids;
	}
	public void setSinger_res_ids(String singer_res_ids) {
		this.singer_res_ids = singer_res_ids;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLayout_start() {
		return layout_start;
	}
	public void setLayout_start(String layout_start) {
		this.layout_start = layout_start;
	}
	public String getLayout_end() {
		return layout_end;
	}
	public void setLayout_end(String layout_end) {
		this.layout_end = layout_end;
	}
	public List<IptvResVer> getList() {
		return list;
	}

	public String getFposter() {
		return fposter;
	}

	public void setFposter(String fposter) {
		this.fposter = fposter;
	}

	public void setList(List<IptvResVer> list) {
		this.list = list;
//		if(list!=null)
//		for(IptvResVer l : list){
//			l.setParent(this);
//		}
	}
	public Integer getLayout_line() {
		return layout_line;
	}
	public void setLayout_line(Integer layout_line) {
		this.layout_line = layout_line;
	}
	public Boolean getLayout_show_title() {
		return layout_show_title;
	}
	public void setLayout_show_title(Boolean layout_show_title) {
		this.layout_show_title = layout_show_title;
	}
	public Boolean getLayout_show_child_title() {
		return layout_show_child_title;
	}
	public void setLayout_show_child_title(Boolean layout_show_child_title) {
		this.layout_show_child_title = layout_show_child_title;
	}
	public Boolean getLayout_big() {
		return layout_big;
	}
	public void setLayout_big(Boolean layout_big) {
		this.layout_big = layout_big;
	}
	public Boolean getLayout_regular() {
		return layout_regular;
	}
	public void setLayout_regular(Boolean layout_regular) {
		this.layout_regular = layout_regular;
	}
	public Long getLink_vid() {
		return link_vid;
	}
	public void setLink_vid(Long link_vid) {
		this.link_vid = link_vid;
	}
	public Long getLink_rid() {
		return link_rid;
	}
	public void setLink_rid(Long link_rid) {
		this.link_rid = link_rid;
	}
	public Boolean getCommit() {
		return commit;
	}
	public void setCommit(boolean commit) {
		this.commit = commit;
	}
	public IptvResVer getParent() {
		return parent;
	}
	public void setParent(IptvResVer parent) {
		this.parent = parent;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getChild_num() {
		return child_num;
	}
	public void setChild_num(Integer child_num) {
		this.child_num = child_num;
	}
	public Long getLink_test_vid() {
		return link_test_vid;
	}
	public void setLink_test_vid(Long link_test_vid) {
		this.link_test_vid = link_test_vid;
	}
	public Long getLink_prod_vid() {
		return link_prod_vid;
	}
	public void setLink_prod_vid(Long link_prod_vid) {
		this.link_prod_vid = link_prod_vid;
	}
	public Long getSinger_rid() {
		return singer_rid;
	}
	public void setSinger_rid(Long singer_rid) {
		this.singer_rid = singer_rid;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public Boolean getLink_version_disable() {
		return link_version_disable;
	}
	public void setLink_version_disable(Boolean link_version_disable) {
		this.link_version_disable = link_version_disable;
	}
	public Boolean getSinger_global_disable() {
		return singer_global_disable;
	}
	public void setSinger_global_disable(Boolean singer_global_disable) {
		this.singer_global_disable = singer_global_disable;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link_vid == null) ? 0 : link_vid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IptvResVer other = (IptvResVer) obj;
		if (link_vid == null) {
			if (other.link_vid != null)
				return false;
		} else if (!link_vid.equals(other.link_vid))
			return false;
		return true;
	}

	public String toSingerString() {
		return "IptvResVer [name=" + name + ", bposter=" + bposter
				+ ", sposter=" + sposter + ", singer_name=" + singer_name
				+ ", res_id=" + res_id + ", music_id=" + music_id + "]";
	}

	public String toSongString() {
		return "IptvResVer [name=" + name + ", bposter=" + bposter
				+ ", sposter=" + sposter + ", singer_res_ids=" + singer_res_ids
				+ ", rid=" + rid + ", res_id=" + res_id + ", music_id="
				+ music_id + "]";
	}


	@Override
	public String toString() {
		return "IptvResVer [rid=" + rid + ", vid=" + vid + ", name=" + name + ", link_prid=" + link_prid + ", link_crid=" + link_crid + ", link_vid=" + link_vid + ", link_rid="
				+ link_rid + ", link_test_vid=" + link_test_vid + ", link_prod_vid=" + link_prod_vid + ", type=" + type + ", child_type="
				+ child_type + ", prod_vid=" + prod_vid + ", test_vid=" + test_vid + "]";
	}

	public  String doMajor(){
		return  getRid()+"|"+getVid()+"|"+getType()+"|"+getChild_type()+"|"+getName();
	}

}
