package util;

import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class OkHttpUtil {
    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String PUT = "PUT";
    public final static String DELETE = "DELETE";
    public final static String PATCH = "PATCH";
    private final static String UTF8 = "UTF-8";
    private final static String GBK = "GBK";

    private final static String DEFAULT_CHARSET = UTF8;
    private final static String DEFAULT_METHOD = GET;
    private final static String DEFAULT_MEDIA_TYPE = "application/json";
    private final static boolean DEFAULT_LOG = false;

    private final static OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(360, 30, TimeUnit.SECONDS))
            .connectTimeout(2L, TimeUnit.SECONDS)
            .readTimeout(3L, TimeUnit.SECONDS)
            .writeTimeout(3L, TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
//            .sslSocketFactory(sslSocketFactory(), Util.platformTrustManager())
            .build();


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("k", "v");
        try {
            String s = execute(OkHttp.builder()
                    .url("http://www.baidu.com")
                    .method(GET)
                    .requestLog(true)
                    .responseLog(true)
                    .build());
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GET请求
     * @param url URL地址
     * @return
     */
    public static String get(String url) {
        return execute(OkHttp.builder().url(url).build());
    }

    /**
     * GET请求
     * @param url URL地址
     * @return
     */
    public static String getAddHeader(String url, Map<String, String> headerMap) {
        return execute(OkHttp.builder().headerMap(headerMap).url(url).build());
    }

    /**
     * GET请求
     * @param url URL地址
     * @return
     */
    public static String getAddHeader(String url, Map<String, String> headerMap, boolean reqLog, boolean resLog) {
        return execute(OkHttp.builder().headerMap(headerMap).url(url).requestLog(reqLog).responseLog(resLog).build());
    }

    /**
     * GET请求
     * @param url URL地址
     * @return
     */
    public static String get(String url, String responseCharset) {
        return execute(OkHttp.builder().url(url).responseCharset(responseCharset).build());
    }

    /**
     * 带查询参数的GET查询
     * @param url      URL地址
     * @param queryMap 查询参数
     * @return
     */
    public static String get(String url, Map<String, String> queryMap) {
        return execute(OkHttp.builder().url(url).queryMap(queryMap).build());
    }

    /**
     * 带查询参数的GET查询
     * @param url      URL地址
     * @param queryMap 查询参数
     * @return
     */
    public static String get(String url, Map<String, String> queryMap, String responseCharset) {
        return execute(OkHttp.builder().url(url).queryMap(queryMap).responseCharset(responseCharset).build());
    }
    /**
     * 带查询参数的GET查询 ("application/json")
     * @param url      URL地址
     * @param queryMap 查询参数
     * @param responseCharset : 设置response返回的编码，用于: result = new String(response.body().bytes(), responseCharset); 默认："UTF-8"
     */
    public static String get(String url, Map<String, String> headerMap, Map<String, String> queryMap, String responseCharset) {
        OkHttp build = OkHttp.builder()
                .url(url)
                .method(GET)
                .headerMap(headerMap)
                .queryMap(queryMap)
                .responseCharset(responseCharset)
                .mediaType(DEFAULT_MEDIA_TYPE)
                .build();
        return execute(build);
    }
    /**
     * POST application/json
     * @param url
     * @param obj: 入参对象会被json转换，传参时不需要再toJsonString了
     * @return
     */
    public static String postJson(String url, Object obj) {
        return execute(OkHttp.builder().url(url).method(POST).data(JacksonUtil.toJsonStr(obj)).mediaType("application/json").build());
    }
    public static String postJson(String url, Map<String, String> headerMap, Map<String, String> queryMap) {
        return postJson(url, headerMap, queryMap, null);
    }
    /**
     * POST application/json
     * @param url 目标url
     * @param headerMap 请求头
     * @param queryMap 查询参数
     * @param requestBody: 入参对象会被json转换，传参时不需要再toJsonString了
     * @return
     */
    public static String postJson(String url, Map<String, String> headerMap, Map<String, String> queryMap, Object requestBody) {
        OkHttp build = OkHttp.builder()
                .url(url)
                .method(POST)
                .queryMap(queryMap)
                .data(JacksonUtil.toJsonStr(requestBody))
                .mediaType(DEFAULT_MEDIA_TYPE)
                .headerMap(headerMap)
                .build();
        return execute(build);
    }

    public static String postJsonByBasicAuth(String url, Object obj, String appId, String appSecret) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((appId + ":" + appSecret).getBytes(StandardCharsets.UTF_8)));
        OkHttp build = OkHttp.builder()
                .url(url)
                .method(POST)
                .data(JacksonUtil.toJsonStr(obj))
                .mediaType("application/json")
                .headerMap(headerMap)
                .build();
        return execute(build);
    }

    /**
     * POST application/x-www-form-urlencoded
     * @param url
     * @param formMap
     * @return
     */
    public static String postForm(String url, Map<String, String> formMap) {
        String data = "";
        if (MapUtils.isNotEmpty(formMap)) {
            data = formMap.entrySet().stream().map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue())).collect(Collectors.joining("&"));
        }
        return execute(OkHttp.builder().url(url).method(POST).data(data).mediaType("application/x-www-form-urlencoded").build());
    }

    private static String post(String url, String data, String mediaType, String responseCharset) {
        return execute(OkHttp.builder().url(url).method(POST).data(data).mediaType(mediaType).responseCharset(responseCharset).build());
    }

    public static String postXmlFor4GApi(String url,Map<String, String> formMap) {
        StringBuffer data = new StringBuffer();
        //拼接前缀
        data.append("<operation_in><content>");
        if (MapUtils.isNotEmpty(formMap)) {
            formMap.entrySet().stream().forEach(t->{
                data.append("<" + t.getKey() + ">" + t.getValue() + "</" + t.getKey() + ">");
            });
        }
        //拼接前缀
        data.append("</content></operation_in>");
        return execute(OkHttp.builder().url(url).method(POST).data(data.toString()).mediaType("application/xml").build());
    }

    /**
     * 通用执行方法
     */
    private static String execute(OkHttp okHttp) {
        // 返回值
        String result = "";
        try {
            if (StringUtils.isEmpty(okHttp.requestCharset)) {
                okHttp.requestCharset = DEFAULT_CHARSET;
            }
            if (StringUtils.isEmpty(okHttp.responseCharset)) {
                okHttp.responseCharset = DEFAULT_CHARSET;
            }
            if (StringUtils.isEmpty(okHttp.method)) {
                okHttp.method = DEFAULT_METHOD;
            }
            if (StringUtils.isEmpty(okHttp.mediaType)) {
                okHttp.mediaType = DEFAULT_MEDIA_TYPE;
            }
            if (okHttp.requestLog) {// 记录请求日志
                log.info("request log: {}", okHttp.toString());
            }

            // 获取请求URL
            String url = okHttp.url;
            // 创建请求
            Request.Builder builder = new Request.Builder();
            if (MapUtils.isNotEmpty(okHttp.queryMap)) {
                String queryParams = okHttp.queryMap.entrySet().stream().map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue())).collect(Collectors.joining("&"));
                url = String.format("%s%s%s", url, url.contains("?") ? "&" : "?", queryParams);
            }
            builder.url(url);
            // 设置请求头
            if (MapUtils.isNotEmpty(okHttp.headerMap)) {
                okHttp.headerMap.forEach(builder::addHeader);
            }

            // 设置请求类型: mediaType
            String method = okHttp.method.toUpperCase();
            String mediaType = String.format("%s;charset=%s", okHttp.mediaType, okHttp.requestCharset);
            if (method.equals(GET)) {
                builder.get();
            } else if (ArrayUtils.contains(new String[]{POST, PUT, DELETE, PATCH}, method)) {
                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), okHttp.data);
                builder.method(method, requestBody);
            } else {
                log.error("---未设置请求method");
                //            throw new GlobalException("未设置请求method");
            }

            try (Response response = client.newCall(builder.build()).execute()){
                byte[] bytes = response.body().bytes();
                result = new String(bytes, okHttp.responseCharset);
                if (okHttp.responseLog) {// 记录返回日志
                    Map<String, List<String>> resHeaderMap = response.headers().toMultimap();
                    log.info("---result: {}, headers={}", result, JacksonUtil.toJsonStr(resHeaderMap));
                }
            } catch (Exception e) {
                log.error("http execute error! url={}, {}", url, e);
            }

        } catch (Exception e) {
            log.error("HTTP execute error! url={}, {}", okHttp.url, e);
        }
        return result;
    }

    @Builder
    @ToString(exclude = {"requestCharset", "responseCharset", "requestLog", "responseLog"})
    static class OkHttp {
        private String url;
        private String method = DEFAULT_METHOD;
        private String data;
        private String mediaType = DEFAULT_MEDIA_TYPE;
        private Map<String, String> queryMap;
        private Map<String, String> headerMap;
        private String requestCharset = DEFAULT_CHARSET;
        private boolean requestLog = DEFAULT_LOG;
        private String responseCharset = DEFAULT_CHARSET;
        private boolean responseLog = DEFAULT_LOG;
    }

}
