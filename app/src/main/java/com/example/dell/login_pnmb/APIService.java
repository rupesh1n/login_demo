package com.example.dell.login_pnmb;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("attendance")
    @FormUrlEncoded
    Call<LoginModelResponse> savePost(@Field("LoginID") String LoginID,
                                      @Field("sessionId") String sessionId,
                                      @Field("Latitude") String Longitute,
                                      @Field("title") String Latitute,
                                      @Field("body") String Location,
                                      @Field("userId") String Address,
                                      @Field("title") String IP,
                                      @Field("body") String Comment
                      );
}
