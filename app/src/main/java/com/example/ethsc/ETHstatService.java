package com.example.ethsc;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ETHstatService {

    @GET("api")
    Call<ETHinfo> getStat(@Query("module") String stats, @Query("action") String ethprice, @Query("api_key") String key);

}
