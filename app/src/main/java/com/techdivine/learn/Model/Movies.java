package com.techdivine.learn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movies {

  @SerializedName("m_id")
  @Expose
  private String mId;
  @SerializedName("m_name")
  @Expose
  private String mName;
  @SerializedName("m_banner_url")
  @Expose
  private String mBannerUrl;
  @SerializedName("link")
  @Expose
  private String link;
  @SerializedName("c_id")
  @Expose
  private String cId;
  @SerializedName("likes")
  @Expose
  private String likes;
  @SerializedName("created_date")
  @Expose
  private String createdDate;
  @SerializedName("url")
  @Expose
  private String url;


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMId() {
    return mId;
  }

  public void setMId(String mId) {
    this.mId = mId;
  }

  public String getMName() {
    return mName;
  }

  public void setMName(String mName) {
    this.mName = mName;
  }

  public String getMBannerUrl() {
    return mBannerUrl;
  }

  public void setMBannerUrl(String mBannerUrl) {
    this.mBannerUrl = mBannerUrl;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getCId() {
    return cId;
  }

  public void setCId(String cId) {
    this.cId = cId;
  }

  public String getLikes() {
    return likes;
  }

  public void setLikes(String likes) {
    this.likes = likes;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

}
