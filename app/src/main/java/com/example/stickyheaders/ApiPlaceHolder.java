package com.example.stickyheaders;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiPlaceHolder {

    @POST("get-deals-coupons")
    Call<ResponseBody> menulistallitem(@Body JsonObject jsonObject);

}
