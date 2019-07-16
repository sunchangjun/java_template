package hk.reco.music.iptv.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	
	public static byte[] getImageBytes(String url) throws Exception {
		InputStream is = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			//对于firefox的浏览器,腾讯输出的图片是jpg格式的
			conn.setRequestProperty("User-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11 wthx");
			conn.setRequestProperty("Accept","text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
			conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.9,en;q=0.7");
			conn.setRequestProperty("Accept-Charset","gb2312,utf-8;q=0.7,*;q=0.7");
			conn.setRequestProperty("Keep-Alive", "300");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(15 * 1000);
			conn.connect();
			is = conn.getInputStream();
			byte[] b = new byte[10*1024*1024];
			int len = 0;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((len=is.read(b))!=-1) {
				baos.write(b, 0, len);
			}
			return baos.toByteArray();
		}finally{
			if (is != null){
				try{is.close();
				}catch(Exception e){}
			}
		}
	}
	
	public static void main(String[] args){
		try {
			URL url = new URL("http://110.185.117.13/vcloud1049.tc.qq.com/1049_M0113400003lMH1O2txcRb1001630330.f9834.mp4?vkey=68F340A8CC4E2F1254AA7DE2F20D1876F9D13E22F8F3D4863C6F2AA39D474DEA74A25E196F1D4F07696474DD85C5B3BD651EEA97526A83E1D3675574D213982A0B6E9D47DAD0B0F1FD13EE67838CA6F4CA647BBC3DEC896E&stdfrom=1");
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
			httpcon.setRequestMethod("GET");
			long len = httpcon.getContentLengthLong();
			System.out.println(len);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
