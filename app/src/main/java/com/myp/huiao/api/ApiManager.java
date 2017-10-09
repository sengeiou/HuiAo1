package com.myp.huiao.api;

import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.myp.huiao.util.MD5;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.util.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者 by wuliang 时间 16/11/24.
 * <p>
 * 所有的请求控制
 */

public class ApiManager {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static final String TAG = "ApiManager";
    private Retrofit mRetrofit;
    private static final int DEFAULT_TIMEOUT = 10;
    OkHttpClient.Builder builder;

    /**
     * 初始化请求体
     */
    private ApiManager() {
        //手动创建一个OkHttpClient并设置超时时间
        ClearableCookieJar cookieJar =    //此操作将后台的cookie持久化，避免上传文件清空了后台的cookie
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Utils.getContext()));
        builder = new OkHttpClient.Builder();
        //手动创建一个OkHttpClient并设置超时时间
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.cookieJar(cookieJar);
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, "log: " + message);
            }
        });
        loggingInterceptor.setLevel(level);
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(postInterceptor);
//        builder.addNetworkInterceptor(mNetInterceptor);  //添加网络拦截器
    }

    private static class SingletonHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    //获取单例
    public static ApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取请求代理
     *
     * @param service
     * @param url
     * @param <T>
     * @return
     */
    public <T> T configRetrofit(Class<T> service, String url) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit.create(service);
    }


    Interceptor postInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            if (request.body() instanceof MultipartBody) {   //上传文件时，不进入此过滤器
                return chain.proceed(request);
            }
            String postBodyString = bodyToString(request.body());
            String params = "";
            if (postBodyString.indexOf("&") != -1) {
                String[] post = postBodyString.split("&");
                SortedMap<String, Object> keyAndValues = new TreeMap<>();
                if (post != null && post.length != 0) {
                    for (String param : post) {
                        String key = param.split("=")[0];
                        String value = param.split("=")[1];
                        keyAndValues.put(key, value);
                    }
                }
                for (String key : keyAndValues.keySet()) {
                    if ("_sig".equals(key)) {   //鼎新接口不需要加参数
                        return chain.proceed(request);
                    }
                    params += key + "=" + keyAndValues.get(key) + "&";
                }
            }
            //避免中文生成签名时和后台的编码冲突
            String encodeParm = StringUtils.isEmpty(params) ? postBodyString : params.substring(0, params.length() - 1);
            String sign = MD5.strToMd5Low32(URLEncoder.encode(URLDecoder.decode(encodeParm, "UTF-8"), "UTF-8"));
            RequestBody formBody = new FormBody.Builder()
                    .add("sign", sign.replaceAll("\\*", "%2A").replaceAll("\\+", "%20"))
                    .build();
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            request = requestBuilder
                    .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
                            postBodyString))
                    .build();
            return chain.proceed(request);
        }
    };


    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
