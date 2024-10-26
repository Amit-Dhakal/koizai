package com.koizai.commonservice.currency.service;


import com.koizai.commonservice.common.KoizaiException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RestApiService {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private OkHttpClient okHttpClient;

    private long connectionTimeout = 20000;
    private long readTimeout = 10000;

    public RestApiService() {

        this.okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    public String doGet(String url) throws Exception {
        String result = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            result = response.body().string();
            if (result != null && result.isEmpty()) {
                throw new KoizaiException(KoizaiException.API_EMPTY_RESPONSE, "Empty response from " + url);
            }

        } else {
            String err = response.code() + " - " + (response.message().isEmpty() ? "No message" : response.message());
            throw new KoizaiException(KoizaiException.API_FAIL_RESPONSE, "API error : " + err);
        }
        return result;
    }

    public String authGet(String url, String token) throws Exception {
        String result = null;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            result = response.body().string();
            if (result != null && result.isEmpty()) {
                throw new KoizaiException(KoizaiException.API_EMPTY_RESPONSE, "Empty response from " + url);
            }

        } else {
            String err = response.code() + " - " + (response.message().isEmpty() ? "No message" : response.message());
            throw new KoizaiException(KoizaiException.API_FAIL_RESPONSE, "API error : " + err);
        }
        return result;
    }

    public String authPost(String url, RequestBody requestBody, String token) throws Exception {
        String result = null;
        Request request = new Request.Builder()
                .post(requestBody)
                .addHeader("Authorization", token)
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            result = response.body().string();
            if (result != null && result.isEmpty()) {
                throw new KoizaiException(KoizaiException.API_EMPTY_RESPONSE, "Empty response from " + url);
            }

        } else {
            String err = response.code() + " - " + (response.message().isEmpty() ? "No message" : response.message());
            throw new KoizaiException(KoizaiException.API_FAIL_RESPONSE, "API error : " + err);
        }

        return result;
    }


//    public ResponseBody doPost1(String uri, RequestBody requestBody) throws Exception {
//        ResponseBody result = null;
//        String url = host + uri;
//        Request request = new Request.Builder()
//                .post(requestBody)
//                .addHeader("Authorization", "Bearer " + token)
//                .url(url)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        Response response = call.execute();
//        if (response.isSuccessful()) {
//            result = response.body();
//            if (result!=null && result.string().isEmpty()) {
//                throw new KoizaiException(KoizaiException.IB_EMPTY_RESPONSE, "Empty response from the middle man");
//            }
//
//        }else{
//            String err = response.code() + " - "+ (response.message().isEmpty()?"No message": response.message());
//            throw new KoizaiException(KoizaiException.IB_FAIL_RESPONSE,"IBH middle-man API error : " + err);
//        }
//
//        return result;
//    }
//
//
//
//    public void closeResponseBody(ResponseBody responseBody){
//        try{
//            if(responseBody!=null){
//                responseBody.close();
//            }
//        }catch (Exception ex){
//            log.error(ex.getMessage());
//        }
//    }


}
