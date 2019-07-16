package hk.reco.music.iptv.common.utils;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {

	public static String post(String url, List<NameValuePair> params) throws Exception{
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = builder.build();
		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,"utf-8");
			return content;
		} finally {
			httpclient.close();
		}
	}
	
}
