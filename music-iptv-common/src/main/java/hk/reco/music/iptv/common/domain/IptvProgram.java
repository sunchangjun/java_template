package hk.reco.music.iptv.common.domain;

/**
 * 〈媒体类 包括mv和song〉
 *
 * @author comelk
 * @create 2019/2/22
 */
public class IptvProgram extends  IptvRes{
	private static final long serialVersionUID = 5343621925693755792L;
	/**
     * 播放地址
     */
    private String playUrl;
    /**
     * 媒资注入后运营商返回的id
     */
    private String assetId;
    /**
     * 用于存储路径或其他必须属性
     */
    private String path;
    /**
     * 单曲价格（0.00为免费）
     */
    private String price;
    /**
     * 歌手名称
     */
    private String director;
    /**
     * 保留字段，用于存储其他可能用到的未知字段
     */
    private String other;

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
