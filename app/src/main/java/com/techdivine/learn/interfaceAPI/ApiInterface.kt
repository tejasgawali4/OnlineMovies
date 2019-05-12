package com.techdivine.learn.interfaceAPI

import com.techdivine.learn.Model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiInterface {
  // login webservice
  //  https://api.myjson.com/bins/17fx2e
  //  https://api.myjson.com/bins/13myie

  @GET("data.json")
  abstract fun getMovielist(@Query("key") key: String): Call<List<Movies>>

}
