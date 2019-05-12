package com.techdivine.learn
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.techdivine.learn.Adapter.MoviesListAdapter
import com.techdivine.learn.Model.Movies
import com.techdivine.learn.api.APIClient
import com.techdivine.learn.interfaceAPI.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

  internal lateinit var apiInterface: ApiInterface
  var MovieList: List<Movies>? = null
  var recyclerView : RecyclerView? = null
  var progressBar : ProgressBar? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    progressBar = findViewById<ProgressBar>(R.id.progressBar)

    recyclerView = findViewById<RecyclerView>(R.id.movies_r_list) as RecyclerView

    recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

    getLatestUpdate()

    recyclerView!!.setOnClickListener {
      Log.d("On Click ..." ," ")
    }
  }

  private fun getLatestUpdate() {

    progressBar!!.visibility == View.VISIBLE

    apiInterface = APIClient.client.create(ApiInterface::class.java)

    val call1 = apiInterface.getMovielist("12345")

    call1.enqueue(object : Callback<List<Movies>> {
      override fun onResponse(call: Call<List<Movies>>, response: Response<List<Movies>>) {

        MovieList = response.body() as List<Movies>

        Log.d("Response :- " ," " + MovieList.toString())
        Log.d("Movies Length :- " ," " + response.body())

        try {

          if(MovieList!!.isEmpty()){
            progressBar!!.visibility == View.VISIBLE
          }else{
            progressBar!!.visibility == View.GONE
          }

          val rvAdapter = MoviesListAdapter(MovieList as List<Movies>, applicationContext)
//        set the recyclerView to the adapter
          recyclerView!!.adapter = rvAdapter;

        } catch (e: Exception) {
          Log.d("onResponse", "There is an error")
          e.printStackTrace()
        }

      }
      override fun onFailure(call: Call<List<Movies>>, t: Throwable) {
        call.cancel()
      }
    })
    //end cal
  }

}
