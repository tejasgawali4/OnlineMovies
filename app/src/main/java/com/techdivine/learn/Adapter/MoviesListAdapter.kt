package com.techdivine.learn.Adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.techdivine.learn.Model.Movies
import com.techdivine.learn.R

class MoviesListAdapter(private val MovieList: List<Movies>,private val context : Context) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>()
{

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
    val v = LayoutInflater.from(p0?.context).inflate(R.layout.m_listview, p0, false)

    return ViewHolder(v)
  }

  override fun getItemCount(): Int {
    return MovieList.size
  }

  override fun onBindViewHolder(v: ViewHolder, p1: Int) {

    v.name?.text = MovieList[p1].mName.toString()

    Glide.with(context).load(MovieList[p1].mBannerUrl.toString()).into(v.movie_poster);

  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.mName)
    val movie_poster = itemView.findViewById<ImageView>(R.id.movie_poster)
  }
}
