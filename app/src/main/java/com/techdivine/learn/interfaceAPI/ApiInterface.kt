package com.techdivine.learn.interfaceAPI

import com.techdivine.learn.Model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


public interface ApiInterface {
  // login webservice
  @GET("index.php")
  abstract fun getMovie_list(@Query("username") Society_No: String): Call<List<Movies>>
}
