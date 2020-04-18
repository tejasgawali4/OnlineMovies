package com.techdivine.learn.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.techdivine.learn.Model.Movies
import com.techdivine.learn.R
import com.techdivine.learn.StreamGiven


class MoviesListAdapter(private val MovieList: List<Movies>,context : Context) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>()
{

  private var mcontext: Context? = null

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
    val v = LayoutInflater.from(p0?.context).inflate(R.layout.m_listview, p0, false)
    this.mcontext = p0?.context;
    return ViewHolder(v)
  }

  override fun getItemCount(): Int {
    return MovieList.size
  }

  override fun onBindViewHolder(v: ViewHolder, p1: Int) {

    v.name?.text = MovieList[p1].mName.toString()

    Glide.with(mcontext).load(MovieList[p1].mBannerUrl.toString()).into(v.movie_poster);

    v.btnWathc.setOnClickListener {
      Log.d("onItemClickEvent :- " , "" + MovieList[p1].mName.toString())
      val intent = Intent(mcontext, StreamGiven::class.java)
      // To pass any data to next activity
      intent.putExtra("url", MovieList[p1].url.toString())
      Log.d("url :- " , "" + MovieList[p1].url.toString())

      // start your next activity
      startActivity(v.btnWathc.context,intent,null)
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name = itemView.findViewById<TextView>(R.id.mName)
    val movie_poster = itemView.findViewById<ImageView>(R.id.movie_poster)
    val btnWathc = itemView.findViewById<Button>(R.id.btnWatchNow)

  }

}
