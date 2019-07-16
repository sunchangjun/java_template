package hk.reco.music.iptv.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http、https 请求工具类
 *
 * @Author wangpq
 * @Param
 * @Date 2019/4/3 10:47
 */
public class HttpsUtil {

    private final static String CHARSET = "utf-8";

    private final static Integer ERROR_CODE = -1;

    /**
     * doGet
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url, header, param]
     * @Date 2019/4/3 11:25
     */
    public static HttpResp doGet(String url, Map<String, String> header, Map<String, String> param) {
        HttpResp httpResp = new HttpResp();
        long start = System.currentTimeMillis();
        httpResp.setStart(start);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = createSSLClientDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                param.forEach(builder::addParameter);
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (null != header) {
                header.forEach(httpGet::setHeader);
            }
            setTimeConfig(httpGet);
            // 执行请求
            response = httpClient.execute(httpGet);
            httpResp.setStatusCode(response.getStatusLine().getStatusCode());
            httpResp.setResp(EntityUtils.toString(response.getEntity(), CHARSET));
        } catch (Exception e) {
            httpResp.setStatusCode(ERROR_CODE);
            httpResp.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            resourceClosed(response, httpClient);
            long cost = System.currentTimeMillis() - start;
            httpResp.setCost(cost);
        }
        return httpResp;
    }

    /**
     * 连接配置
     *
     * @return void
     * @Author wangpq
     * @Param [base]
     * @Date 2019/4/3 11:36
     */
    private static void setTimeConfig(HttpRequestBase base) {
        int soTimeout = 30000;
        int connTimeout = 30000;
        int connMgrTimeout = 30000;

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimeout)
                .setConnectionRequestTimeout(connMgrTimeout)
                .setSocketTimeout(soTimeout).build();
        base.setConfig(requestConfig);

    }

    /**
     * 资源关闭
     *
     * @return void
     * @Author wangpq
     * @Param [response, httpClient]
     * @Date 2019/4/3 11:21
     */
    private static void resourceClosed(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        try {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * doGet
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url]
     * @Date 2019/4/3 11:25
     */
    public static HttpResp doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * doGet
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url, header]
     * @Date 2019/4/3 11:25
     */
    public static HttpResp doGet(String url, Map<String, String> header) {
        return doGet(url, header, null);
    }

    /**
     * doPost
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url, header, param]
     * @Date 2019/4/3 11:33
     */
    public static HttpResp doPost(String url, Map<String, String> header, Map<String, String> param) {
        HttpResp httpResp = new HttpResp();
        long start = System.currentTimeMillis();
        httpResp.setStart(start);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = createSSLClientDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (null != header) {
                header.forEach(httpPost::setHeader);
            }
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                param.forEach((k, v) -> paramList.add(new BasicNameValuePair(k, v)));
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, CHARSET);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            httpResp.setStatusCode(response.getStatusLine().getStatusCode());
            httpResp.setResp(EntityUtils.toString(response.getEntity(), CHARSET));
        } catch (Exception e) {
            httpResp.setStatusCode(ERROR_CODE);
            httpResp.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            resourceClosed(response, httpClient);
            long cost = System.currentTimeMillis() - start;
            httpResp.setCost(cost);
        }
        return httpResp;
    }

    /**
     * doPost
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url, header]
     * @Date 2019/4/3 11:33
     */
    public static HttpResp doPost(String url, Map<String, String> header) {
        return doPost(url, header, null);
    }


    /**
     * doPost
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url]
     * @Date 2019/4/3 11:33
     */
    public static HttpResp doPost(String url) {
        return doPost(url, null, null);
    }

    /**
     * doPostJson
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url, json]
     * @Date 2019/4/3 12:04
     */
    public static HttpResp doPostJson(String url, String json) {
        return doPostJson(url, null, json, null);
    }

    /**
     * doPostJson
     *
     * @return hk.reco.music.iptv.common.utils.HttpResp
     * @Author wangpq
     * @Param [url, header, json]
     * @Date 2019/4/3 12:04
     */
    public static HttpResp doPostJson(String url, Map<String, String> header, String json, ContentType contentType) {
        HttpResp httpResp = new HttpResp();
        long start = System.currentTimeMillis();
        httpResp.setStart(start);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = createSSLClientDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (null != header) {
                header.forEach(httpPost::setHeader);
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(json, contentType == null ? ContentType.APPLICATION_JSON : contentType);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            httpResp.setStatusCode(response.getStatusLine().getStatusCode());
            httpResp.setResp(EntityUtils.toString(response.getEntity(), CHARSET));
        } catch (Exception e) {
            httpResp.setStatusCode(ERROR_CODE);
            httpResp.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            resourceClosed(response, httpClient);
            long cost = System.currentTimeMillis() - start;
            httpResp.setCost(cost);
        }
        return httpResp;
    }

    /**
     * SSL
     *
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @Author wangpq
     * @Param []
     * @Date 2019/4/3 11:59
     */
    private static CloseableHttpClient createSSLClientDefault() {
        try {
            //信任所有
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (xcs, string) -> true).build();
            return HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
                    // http 重试机制
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(3,true))
                    .build();
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException ex) {
            ex.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 将url参数转换成map
     *
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author wangpq
     * @Param [param]  aa=11&bb=22&cc=33
     * @Date 2019/4/24 17:29
     */
    public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = Maps.newHashMap();
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (String param1 : params) {
            String[] p = param1.split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        HttpResp httpResp = doGet("https://www.baidu.com");
        System.out.println(JsonUtils.toJson(httpResp));
    }

}
