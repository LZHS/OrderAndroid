package com.lzhs.order.commonlibrary.interceptor;

import com.lzhs.library.utils.LogUtil;
import com.lzhs.library.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/1 : 11:36 AM<br/>
 */
public class HttpInterceptors {
    /**
     * 有网络的时候缓存请求数据拦截器
     *
     * @return
     */
    public static Interceptor getNetCacheInterceptor() {
        return chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int onlineCacheTime = 30;//在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + onlineCacheTime)
                    .removeHeader("Pragma")
                    .build();
        };
    }

    /**
     * 无网络的时候读取缓存数据拦截器
     *
     * @return
     */
    public static Interceptor getOfflineCacheInterceptor() {
        return chain -> {
            Request request = chain.request();
            if (!NetworkUtils.isAvailableByPing()) {
                int offlineCacheTime = 60 * 60 * 24 * 7;//离线的时候的缓存的过期时间
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                        .build();
            }
            return chain.proceed(request);
        };
    }


    /**
     * 获取一个 网络请求日志打印拦截器
     *
     * @return
     */
    public static Interceptor getLogInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                long startNs = System.nanoTime();
                StringBuffer logMsg = new StringBuffer("****************** Request ******************");
                Request request = chain.request();
                RequestBody requestBody = request.body();
                boolean hasRequestBody = requestBody != null;
                Connection connection = chain.connection();
                logMsg.append("\n* method = " + request.method())
                        .append("\n* url = " + request.url())
                        .append("\n* protocol = " + (connection != null ? " " + connection.protocol() : ""));

                if (hasRequestBody)
                    logMsg.append("\n* contentLength = (" + requestBody.contentLength() + "-byte body)");
                if (hasRequestBody && requestBody.contentType() != null)
                    logMsg.append("\n* Content-Type = " + requestBody.contentType());
                logMsg.append("\n* header = " + getHeader(request.headers()));
                if ("POST".equals(request.body()) &&
                        request.body() instanceof FormBody)
                    logMsg.append("\n* FormBody = " + getFormBody((FormBody) request.body()));
                logMsg.append("\n****************** Response ******************");
                Response response;
                try {
                    response = chain.proceed(request);
                } catch (Exception e) {
                    logMsg.append("\n* HTTP FAILED : " + e);
                    throw e;
                }
                long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
                logMsg.append("\n* requestTime = " + tookMs + "ms");
                getResponsBody(logMsg, response);
                LogUtil.d(logMsg.toString());

                return response;
            }

            private void getResponsBody(StringBuffer logMsg, Response response) throws IOException {
                ResponseBody responseBody = response.peekBody(1024 * 1024);
                long contentLength = responseBody.contentLength();
                logMsg.append("\n* code = " + response.code())
                        .append("\n* response.message = " + (response.message().isEmpty() ? "" : response.message()))
                        .append("\n* bodySize = " + (contentLength != -1 ? contentLength + "-byte" : "unknown-length"))
                        .append("\n* requestBody = " + responseBody.string());
            }


            private String getHeader(Headers headers) {
                String val = "";
                for (int i = 0, count = headers.size(); i < count; i++)
                    if (!"Content-Type".equalsIgnoreCase(headers.name(i))
                            && !"Content-Length".equalsIgnoreCase(headers.name(i)))
                        val += "（ " + headers.name(i) + " -- > " + headers.value(i) + " );";
                return val;
            }

            private String getFormBody(FormBody body) {
                String val = "";
                for (int i = 0; i < body.size(); i++)
                    val += " ( " + body.encodedName(i) + " = " + body.encodedValue(i) + " );";
                return val;
            }
        };
    }


    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
