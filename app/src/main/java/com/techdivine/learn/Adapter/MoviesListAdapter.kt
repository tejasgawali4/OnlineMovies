package com.techdivine.learn.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.techdivine.learn.Model.Movies
import com.techdivine.learn.R

class MoviesListAdapter(val MovieList: List<Movies>) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>()
{

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
    val v = LayoutInflater.from(p0?.context).inflate(R.layout.m_listview, p0, false)
    return ViewHolder(v)
  }

  override fun getItemCount(): Int {
    return MovieList.size
  }

  override fun onBindViewHolder(v: ViewHolder, p1: Int) {

    for (i in MovieList) {
//      Log.d("Movies Id :- " ," " + i.mId)
      v.name?.text = i.mName.toString()

      //v.title_background?.text = i.createdDate.toString()
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.mName)
    val movie_poster = itemView.findViewById<ImageView>(R.id.movie_poster)
  }
}
