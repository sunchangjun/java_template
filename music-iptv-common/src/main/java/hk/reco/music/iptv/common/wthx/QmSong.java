package hk.reco.music.iptv.common.wthx;

public class QmSong extends QmRes{
	
	private Long song_id;
	private String song_name;
	private Long album_id;
	private Long singer_id;
	private String singer_name;
	/**
	 * 添加歌曲播放时间  wpq  2019年6月12日13:54:45
	 */
	private Integer song_play_time;
	private String song_play_url_local;
	private String song_play_url_hq_local;
	private String song_play_url_sq_local;
	private String song_play_url_standard_local;
	private String lyric_local;
	private String public_time;
	private Long mv_id;

	public Long getSong_id() {
		return song_id;
	}
	public void setSong_id(Long song_id) {
		this.song_id = song_id;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public Long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
	}
	public Long getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(Long singer_id) {
		this.singer_id = singer_id;
	}

	public Integer getSong_play_time() {
		return song_play_time;
	}

	public void setSong_play_time(Integer song_play_time) {
		this.song_play_time = song_play_time;
	}

	public String getSong_play_url_local() {
		return song_play_url_local;
	}

	public void setSong_play_url_local(String song_play_url_local) {
		this.song_play_url_local = song_play_url_local;
	}

	public String getSong_play_url_hq_local() {
		return song_play_url_hq_local;
	}

	public void setSong_play_url_hq_local(String song_play_url_hq_local) {
		this.song_play_url_hq_local = song_play_url_hq_local;
	}

	public String getSong_play_url_standard_local() {
		return song_play_url_standard_local;
	}

	public void setSong_play_url_standard_local(String song_play_url_standard_local) {
		this.song_play_url_standard_local = song_play_url_standard_local;
	}
	public String getSong_play_url_sq_local() {
		return song_play_url_sq_local;
	}
	public void setSong_play_url_sq_local(String song_play_url_sq_local) {
		this.song_play_url_sq_local = song_play_url_sq_local;
	}
	public String getLyric_local() {
		return lyric_local;
	}
	public void setLyric_local(String lyric_local) {
		this.lyric_local = lyric_local;
	}

	public String getSinger_name() {
		return singer_name;
	}

	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}

	public String getPublic_time() {
		return public_time;
	}

	public void setPublic_time(String public_time) {
		this.public_time = public_time;
	}
	public Long getMv_id() {
		return mv_id;
	}
	public void setMv_id(Long mv_id) {
		this.mv_id = mv_id;
	}
}
