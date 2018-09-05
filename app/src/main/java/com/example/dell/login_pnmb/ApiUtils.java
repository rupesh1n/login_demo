package com.example.dell.login_pnmb;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://tecsagroup.com/hrmsServices/api/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
