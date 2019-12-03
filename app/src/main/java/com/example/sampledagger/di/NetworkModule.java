package com.example.sampledagger.di;

import java.io.IOException;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final String BASE_URL = "https://randomuser.me";

    @Provides
    public Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Named("http_logging_interceptor")
    public Interceptor provideHttpLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Named("my_custom_interceptor")
    public Interceptor provideCustomInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // Do nothing
                return chain.proceed(chain.request());
            }
        };
    }

    @Provides
    public OkHttpClient provideOkHttpClient(
            @Named("http_logging_interceptor") Interceptor httpLoggingInterceptor,
            @Named("my_custom_interceptor") Interceptor customInterceptor
    ) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(customInterceptor)
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(
            Converter.Factory converterFactor,
            CallAdapter.Factory callAdapterFactory,
            OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converterFactor)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build();
    }
}
