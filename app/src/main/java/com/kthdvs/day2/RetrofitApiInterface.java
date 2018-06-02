package com.kthdvs.day2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aayeshs on 6/2/18.
 */

public interface RetrofitApiInterface {
    @GET("api.php")
    Call<List<MenuModel>>getMenu();
}
