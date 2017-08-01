package yswl.com.testmvp.TestOkhttp;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by kangpAdministrator on 2017/5/10 0010.
 * Emial kangpeng@yunhetong.net
 */

public class TestOkHttp {

    public static void get(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();//m默认

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = builder.build();

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public static void post(String url , Map<String,Object> param){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(20*1000, TimeUnit.SECONDS)
                .readTimeout(20*1000,TimeUnit.SECONDS)
                .writeTimeout(20*1000,TimeUnit.SECONDS)
//                .cache(new Cache())//post方法不能缓存
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                }).build();
//        RequestBody postRequest = new
//        okHttpClient.newCall()

    }
}
