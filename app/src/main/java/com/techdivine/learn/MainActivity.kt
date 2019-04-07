package com.techdivine.learn
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    recyclerView = findViewById<RecyclerView>(R.id.movies_r_list) as RecyclerView

    recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

    val btnShow = findViewById<Button>(R.id.button)
    btnShow?.setOnClickListener {
      Toast.makeText(this@MainActivity,"You clicked me.", Toast.LENGTH_LONG).show()

      val intent = Intent(this@MainActivity, StreamGiven::class.java)
      intent.putExtra("url", MovieList?.get(0)?.link)
      startActivity(intent)
    }
    getLatestUpdate()

    // Usage:
    recyclerView!!.addOnItemClickListener(object: OnItemClickListener {
      override fun onItemClicked(position: Int, view: View) {
        Log.d("position :- " ," " + position)
          // To pass any data to next activity
          // start your next activity
//        val intent = Intent(this@MainActivity, StreamGiven::class.java)
//        intent.putExtra("url", MovieList?.get(0)?.link)
//        startActivity(intent)
      }
    })

  }

  private fun getLatestUpdate() {

    apiInterface = APIClient.client.create(ApiInterface::class.java)

    val call1 = apiInterface.getMovie_list("cj")

    call1.enqueue(object : Callback<List<Movies>> {
      override fun onResponse(call: Call<List<Movies>>, response: Response<List<Movies>>) {

        MovieList = response.body() as List<Movies>

        Log.d("Response :- " ," " + MovieList.toString())
        Log.d("Movies Length :- " ," " + response.body())

        try {

          val rvAdapter = MoviesListAdapter(MovieList as List<Movies>)
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

  interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
  }

  fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
      override fun onChildViewAttachedToWindow(p0: View) {
        val holder = getChildViewHolder(p0)
        onClickListener.onItemClicked(holder.adapterPosition, p0)
      }

      override fun onChildViewDetachedFromWindow(p0: View) {
        p0?.setOnClickListener(null)
      }
    })
  }

}
