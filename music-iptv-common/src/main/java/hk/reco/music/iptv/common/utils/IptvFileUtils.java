package hk.reco.music.iptv.common.utils;

import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.exception.IptvError;
import hk.reco.music.iptv.common.ssh.SshTransfer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 文件服务
 *
 * @author zhangsl
 * @date 2019年2月28日
 */
public class IptvFileUtils {

	private static String root;
	private static String httpPrefix;
	private static final String separator = "/";
	private static boolean iptvCodePrefix;


	/**
	 * 初始化文件服务工具
	 * @param root
	 * @param httpPrefix
	 */
	public static void initFileEnv(String root, String httpPrefix, boolean iptvCodePrefix) {
		IptvFileUtils.root = root;
		IptvFileUtils.httpPrefix = httpPrefix;
		IptvFileUtils.iptvCodePrefix = iptvCodePrefix;
	}

	/**
	 * 保存文件
	 * @param file
	 * @return
	 * @throws IptvBusinessException
	 */
	public static String saveFile(MultipartFile file, HttpServletRequest req, String ip, String username, String password)
			throws IptvBusinessException {
		if (file.isEmpty()) {
			return null;
		}
		String path = null;
		try {
			byte[] bytes = file.getBytes();
			path = createFileAbsPath(bytes);
			// 2019年6月18日16:52:09  wpq 因存在多机部署 所以直接走ssh
//			boolean islocal = IptvMsicUtils.isLocalHost(req);
//			if(islocal){
				InputStream is = new ByteArrayInputStream(bytes);
				SshTransfer.transfer(ip, username, password, is, path);
//			}else{
//				Path dir = Paths.get(path.substring(0,path.lastIndexOf(File.separator)));
//				if(Files.notExists(dir)){
//					Files.createDirectories(dir);
//				}
//				Files.write(Paths.get(path), bytes);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IptvBusinessException(IptvError.UPLOAD_IMAGE_FAIL);
		}
		return getRelative(path);
	}

	/**
	 * 绝对地址转成相对地址
	 * @param full
	 * @return
	 */
	public static String getRelative(String full) {
		return full.substring(root.length());
	}

	/**
	 * http绝对地址转相对地址
	 * @param url
	 * @return
	 */
	public static String httpToRelative(String url){
		return url.substring(httpPrefix.length());
	}

	/**
	 * 创建文件绝对路径,注意这里的url暂时只能是本地文件系统类型 TODO: 改成适合http下载的
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String createFileAbsPath(String url) throws Exception {
		String path = root + genRandomLevelFolder() + separator
				+ genUuidFilename();
		byte[] bs = new byte[4];
		FileInputStream fis = new FileInputStream(url);
		fis.read(bs);
		fis.close();
		String suffix = ImageTypeUtils.getPicType(bs);
		if (suffix != null) {
			path += "." + suffix;
		}
		return path;
	}

	/**
	 * 创建文件的绝对路径
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	public static String createFileAbsPath(byte[] bs) throws Exception {
		String path = root + genRandomLevelFolder() + separator
				+ genUuidFilename();
		String suffix = ImageTypeUtils.getPicType(bs);
		if (suffix != null) {
			path += "." + suffix;
		}
		return path;
	}

	/**
	 * 为对象添加地址前缀
	 * @param vers
	 */
	public static void toHttp(List<IptvResVer> vers) {
		if(vers == null) return;
		for (IptvResVer ver : vers) {
			toHttp(ver);
		}
	}

	public static void toHttp(IptvResVer ver) {
		if(ver != null){
			ver.setSposter(http(ver.getSposter()));
			ver.setBposter(http(ver.getBposter()));
			ver.setPoster(http(ver.getPoster()));
			ver.setFposter(http(ver.getFposter()));
			if(IptvFileUtils.iptvCodePrefix){
				ver.setIptv_code1(http(ver.getIptv_code1()));
			}
			ver.setMedia_lyric(http(ver.getMedia_lyric()));
		}
	}

	/**
	 * 随机产生两个分级目录,如/a/e,/x/x,/t/s
	 * @return
	 */
	private static String genRandomLevelFolder() {
		Random random = new Random();
		char c1 = (char) (97 + random.nextInt(26));
		char c2 = (char) (97 + random.nextInt(26));
		StringBuffer sb = new StringBuffer();
		sb.append(separator);
		sb.append(c1);
		sb.append(separator);
		sb.append(c2);
		return sb.toString();
	}

	/**
	 * 创建36位长的uuid值,用于生成上传文件名
	 * @return
	 */
	private static String genUuidFilename() {
		String rawUuid = UUID.randomUUID().toString();
		return rawUuid.replaceAll("-", "");
	}

	/**
	 * 为相对地址添加前缀
	 * @param poster
	 * @return
	 */
	public static String http(String poster) {
		if (StringUtils.isNotBlank(poster)&&!poster.startsWith("http")) {
			return httpPrefix + poster;
		}
		return poster;
	}


}
