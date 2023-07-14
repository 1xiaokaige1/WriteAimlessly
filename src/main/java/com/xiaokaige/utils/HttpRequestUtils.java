package com.xiaokaige.utils;

import com.stl.im.core.util.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    -------------------------------        execute          -------------------------
    | CloseableHttpClient(请求客服端)|  ---------------->     | CloseableHttpResponse |
    -------------------------------        HttpGet          -------------------------
         /|\                               HttpPost
          |                                   |
          | bulid                             |
          |                                   |property
          |                                   |
    ----------------------                    |      --------------------------
    | HttpClientBuilder  |                    |--->  | RequestConfig(请求配置类)|
    ---------------------                             --------------------------
          |
          |
          |
          |
          |   property   --------------------------------------
          |---------->  |  PoolingHttpClientConnectionManager |
                         --------------------------------------

 */


@Component
public class HttpRequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);
    private final String CHARSET = "UTF-8";
    @Resource
    private RequestConfig requestConfig;
    @Resource
    private CloseableHttpClient httpClient;

    /**
     * get请求
     */
    public String getWithParam(String url, String key, Object value) {
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put(key, value);
        return get(url, paramMap);
    }

    /**
     * get请求
     */
    public String getWithHeader(String url, String key, String value) {
        Map<String, String> headerMap = new HashMap<>(2);
        headerMap.put(key, value);
        return get(url, null, headerMap);
    }

    /**
     * get请求
     */
    public String getWithHeader(String url, Map<String, Object> paramMap, Map<String, String> headerMap) {
        return get(url, paramMap, headerMap);
    }

    /**
     * get请求
     */
    public String get(String url, Object param) {
        return get(url, JsonUtils.toMap(param));
    }

    /**
     * get请求
     */
    public String get(String url, Map<String, Object> map) {
        return get(url, map, null);
    }

    public String get(String url, Map<String, Object> map, Map<String, String> headers) {
        return doRequest(url, HttpMethod.GET, map, null, null, headers);
    }

    /**
     * post请求,参数为查询字符串
     */
    public String postQuery(String url, Object object) {
        return postQuery(url, JsonUtils.toMap(object));
    }

    /**
     * post请求,参数为查询字符串
     */
    public String postQuery(String url, Map<String, Object> map) {
        return doRequest(url, HttpMethod.POST, map, null, ContentType.APPLICATION_JSON, null);
    }

    /**
     * post请求,参数为请求体
     */
    public String post(String url, Object object) {
        return post(url, JsonUtils.toMap(object));
    }

    /**
     * @param url   请求的地址
     * @param query 查询参数
     * @param json  json字符串的String
     * @return http响应的body
     */
    public String post(String url, Map<String, Object> query, String json) {
        return doPostByJsonBody(url, query, json);
    }

    /**
     * post请求,参数为请求体
     */
    public String post(String url, Map<String, Object> map) {
        return doRequest(url, HttpMethod.POST, null, map, ContentType.APPLICATION_JSON, null);
    }

    /**
     * post请求,参数为请求体
     */
    public String postWithHeader(String url, Map<String, Object> map, Map<String, String> headerMap) {
        return doRequest(url, HttpMethod.POST, null, map, ContentType.APPLICATION_JSON, headerMap);
    }

    /**
     * 设置上传的contentType
     *
     * @param url         请求url
     * @param body        请求体
     * @param contentType 请求体格式
     * @return response
     */
    public String post(String url, Map<String, Object> body, ContentType contentType) {
        return doRequest(url, HttpMethod.POST, null, body, contentType, null);
    }

    /**
     * post请求,参数一部分为查询参数，一部分为请求体
     */
    public String post(String url, Map<String, Object> queryParam, Map<String, Object> bodyParam) {
        return doRequest(url, HttpMethod.POST, queryParam, bodyParam, ContentType.APPLICATION_JSON, null);
    }

    /**
     * post请求(文件上传),一部分为查询参数，一部分为请求体（multipart/form-data）
     */
    public String post(String url, Map<String, Object> queryParam, MultipartFile[] fileList) {

        URI uri = createQueryUrl(url, queryParam);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(this.requestConfig);

        String result = null;
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (MultipartFile file : fileList) {
                builder.addBinaryBody("file", file.getBytes(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
            }
            httpPost.setEntity(builder.build());

            CloseableHttpResponse response = httpClient.execute(httpPost);
            result = getResponse(response);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
        return result;
    }

    /**
     * put请求,参数为查询参数
     */
    public String putQuery(String url, Object object) {
        Map<String, Object> map = JsonUtils.toMap(object);
        return putQuery(url, map);
    }

    /**
     * put请求,参数为查询参数
     */
    public String putQuery(String url, Map<String, Object> map) {
        return doRequest(url, HttpMethod.PUT, map, null, null, null);
    }

    /**
     * put请求,参数为请求体
     */
    public String putBody(String url, Map<String, Object> map) {
        return doRequest(url, HttpMethod.PUT, null, map, null, null);
    }

    /**
     * put请求,参数一部分为查询参数，一部分为请求体
     */
    public String put(String url, Map<String, Object> queryParam, Map<String, Object> bodyParam) {
        return doRequest(url, HttpMethod.PUT, queryParam, bodyParam, null, null);
    }

    /**
     * delete请求, 无header
     */
    public String delete(String url, Object object) {
        Map<String, Object> map = JsonUtils.toMap(object);
        return delete(url, map, null);
    }

    /**
     * delete请求, 无header
     */
    public String delete(String url, Map<String, Object> map) {
        return delete(url, map, null);
    }

    /**
     * delete请求, 有header
     */
    public String delete(String url, Object object, Map<String, String> headers) {
        Map<String, Object> map = JsonUtils.toMap(object);
        return doRequest(url, HttpMethod.DELETE, map, null, null, headers);
    }

    /**
     * delete请求, 有header
     */
    public String delete(String url, Map<String, Object> map, Map<String, String> headers) {
        return doRequest(url, HttpMethod.DELETE, map, null, null, headers);
    }

    /**
     * 参数一部分为查询参数，一部分为请求体
     */
    private String doRequest(String url, HttpMethod method, Map<String, Object> queryParam,
                             Map<String, Object> bodyParam, ContentType contentType, Map<String, String> headers) {
        String result = null;
        if (null == contentType) {
            contentType = ContentType.APPLICATION_JSON;
        }

        // 构建uri
        URI uri = getUriWithQuery(url, queryParam);

        // 设置请求体参数
        HttpEntity httpEntity = null;
        if (bodyParam != null) {
            // 构造application/json数据
            if (ContentType.APPLICATION_JSON.equals(contentType)) {
                String paramJson = JsonUtils.toJson(bodyParam);
                httpEntity = new StringEntity(paramJson, ContentType.APPLICATION_JSON);
            } else if (ContentType.APPLICATION_FORM_URLENCODED.equals(contentType)) {
                List<NameValuePair> formParams = new ArrayList<>();
                for (String key : bodyParam.keySet()) {
                    Object value = bodyParam.get(key);
                    NameValuePair valuePair = new BasicNameValuePair(key, value.toString());
                    formParams.add(valuePair);
                }
                try {
                    httpEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.info(e.getLocalizedMessage());
                }
            }

        }

        // 判断请求方式
        switch (method) {
            case GET:
                // 构建、设置HttpGet
                HttpGet httpGet = new HttpGet(uri);
                RequestConfig config =
                        RequestConfig.custom()
                                .setConnectTimeout(5000)
                                .setConnectionRequestTimeout(2000)
                                .setSocketTimeout(5000).build();
                httpGet.setConfig(config);
                if (null != headers) {
                    for (String key : headers.keySet()) {
                        httpGet.setHeader(key, headers.get(key));
                    }
                }

                // 执行、解析请求
                try {
                    CloseableHttpResponse response = httpClient.execute(httpGet);
                    result = getResponse(response);
                } catch (Exception e) {
                    logger.error("Exception: ", e);
                }
                break;

            case POST:
                HttpPost httpPost = new HttpPost(uri);
                String contentTypeStr = contentType.getMimeType() + ";" + "charset=UTF-8";
                httpPost.setHeader("Content-Type", contentTypeStr);
                if (null != headers) {
                    for (String key : headers.keySet()) {
                        httpPost.setHeader(key, headers.get(key));
                    }
                }
                if (httpEntity != null) {
                    httpPost.setEntity(httpEntity);
                }
                try {
                    CloseableHttpResponse response = httpClient.execute(httpPost);
                    result = getResponse(response);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
                break;

            case PUT:
                HttpPut httpPut = new HttpPut(uri);
                httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");
                if (null != headers) {
                    for (String key : headers.keySet()) {
                        httpPut.setHeader(key, headers.get(key));
                    }
                }
                if (httpEntity != null) {
                    httpPut.setEntity(httpEntity);
                }
                try {
                    CloseableHttpResponse response = httpClient.execute(httpPut);
                    result = getResponse(response);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
                break;

            case DELETE:
                HttpDelete httpDelete = new HttpDelete(uri);
                if (null != headers) {
                    for (String key : headers.keySet()) {
                        httpDelete.setHeader(key, headers.get(key));
                    }
                }
                try {
                    CloseableHttpResponse response = httpClient.execute(httpDelete);
                    result = getResponse(response);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
                break;
            default:
                break;
        }

        return result;
    }


    /**
     * 构建host+参数的url
     */
    private URI createQueryUrl(String url, Map<String, Object> map) {
        URI uri = null;
        // 通过URIBuilder构建请求url
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
                }
            }
            uri = uriBuilder.build();
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return uri;
    }

    /**
     * 统一的响应数据处理，将数据以字符串返回
     */
    private String getResponse(CloseableHttpResponse response) {
        String result = null;
        try {
            // 200范围内的才解析
            boolean httpOK =
                    response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() < 300;
            if (httpOK) {
                // DecompressingEntity为具体的响应实体类
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, CHARSET);
                logger.info(result);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage());
                }
            }
        }
        return result;
    }

    private String doPostByJsonBody(String url, Map<String, Object> query, String body) {
        String result = null;
        URI uri = getUriWithQuery(url, query);
        HttpEntity httpEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setEntity(httpEntity);
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            result = getResponse(response);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return result;
    }

    private URI getUriWithQuery(String url, Map<String, Object> query) {
        URI uri = null;
        if (query != null) {
            uri = createQueryUrl(url, query);
        } else {
            try {
                URIBuilder uriBuilder = new URIBuilder(url);
                uri = uriBuilder.build();
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        return uri;
    }

}
