package com.lzhs.order.commonlibrary.di.module;

import com.lzhs.library.Utils;
import com.lzhs.order.commonlibrary.interceptor.HttpInterceptors;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/1 : 5:43 PM<br/>
 */
@Module
public class HttpModule {


    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient provideClient() {
        return OkHttpHelp.getOkHttpClient();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static class OkHttpHelp {
        public static OkHttpClient getOkHttpClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addNetworkInterceptor(HttpInterceptors.getNetCacheInterceptor())
                    .addInterceptor(HttpInterceptors.getOfflineCacheInterceptor())
                    .addNetworkInterceptor(HttpInterceptors.getHttpLoggingInterceptor())
                    .addInterceptor(HttpInterceptors.getLogInterceptor())
                    .cache(getCache())
                    .retryOnConnectionFailure(true);//错误重连
            return builder.build();
        }

        private static Cache getCache() {
            File httpCacheDirectory = new File(Utils.getApp().getCacheDir(), "NetCache");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            return new Cache(httpCacheDirectory, cacheSize);
        }
    }
}
