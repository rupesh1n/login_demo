package com.example.dell.login_pnmb;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/attendance")
    @FormUrlEncoded
    Call<LoginModelResponse> savePost(@Field("LoginID") String LoginID,
                                      @Field("sessionId") String sessionId,
                                      @Field("Longitute") String Longitute,
                                      @Field("Latitute") String Latitute,
                                      @Field("Location") String Location,
                                      @Field("Address") String Address,
                                      @Field("IP") String IP,
                                      @Field("Comment") String Comment
                      );
}
