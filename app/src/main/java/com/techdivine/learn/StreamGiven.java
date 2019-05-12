package com.techdivine.learn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.se_bastiaan.torrentstream.StreamStatus;
import com.github.se_bastiaan.torrentstream.Torrent;
import com.github.se_bastiaan.torrentstream.TorrentOptions;
import com.github.se_bastiaan.torrentstream.TorrentStream;
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class StreamGiven extends AppCompatActivity implements TorrentListener {

  private static final String TORRENT = "Torrent";
  private Button button;
  private ProgressBar progressBar;
  private TextView prgrs;
  private TorrentStream torrentStream;

  private String streamUrl = "magnet:?xt=urn:btih:67F7AEB1BC1B75EE549DAC2A2086249C55AEA246&dn=Kesari%20%282019%29%20Hindi%20720p%20Pre-DVDRip%20x264%20AAC%20%20-%20%5bTeam%20MS%5d&tr=udp%3a%2f%2fcoppersurfer.tk%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.leechers-paradise.org%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.openbittorrent.com%3a80%2fannounce&tr=udp%3a%2f%2ftracker.opentrackr.org%3a1337%2fannounce&tr=udp%3a%2f%2ftracker.zer0day.to%3a1337%2fannounce\n" +
    "magnet:?xt=urn:btih:8BCFE15FCE5EF713083E9996B6A085402DED122D&dn=VA%20-%20Top%20Hits%20%2809.Jan.2019%29%20%5bMp3%20-%20320kbps%5d%20%5bWR%20Music%5d&tr=udp%3a%2f%2ftracker.leechers-paradise.org%3a6969&tr=udp%3a%2f%2fzer0day.ch%3a1337&tr=udp%3a%2f%2fopen.demonii.com%3a1337&tr=udp%3a%2f%2ftracker.coppersurfer.tk%3a6969&tr=udp%3a%2f%2fexodus.desync.com%3a6969&tr=udp%3a%2f%2ftracker.dler.org%3a6969%2fannounce&tr=%2fannounce&tr=%2fannounce&tr=%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2ftracker.filemail.com%3a6969%2fannounce&tr=%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2ftracker.skynetcloud.tk%3a6969%2fannounce&tr=udp%3a%2f%2fretracker.sevstar.net%3a2710%2fannounce&tr=udp%3a%2f%2ftracker.beeimg.com%3a6969%2fannounce&tr=udp%3a%2f%2f00.syo.mx%3a53%2fannounce&tr=%2fannounce&tr=%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2fnewtoncity.org%3a6969%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2fretracker.lanta-net.ru%3a2710%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2ftorrentclub.tech%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.moeking.me%3a6969%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2ftracker.port443.xyz%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.birkenwald.de%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.torrent.eu.org%3a451%2fannounce&tr=udp%3a%2f%2fthetracker.org%3a80%2fannounce&tr=udp%3a%2f%2fexodus.desync.com%3a6969%2fannounce&tr=udp%3a%2f%2fdenis.stalker.upeer.me%3a6969%2fannounce&tr=udp%3a%2f%2fbigfoot1942.sektori.org%3a6969%2fannounce&tr=udp%3a%2f%2fretracker.hotplug.ru%3a2710%2fannounce&tr=udp%3a%2f%2ftracker.filepit.to%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.dyn.im%3a6969%2fannounce&tr=udp%3a%2f%2fexplodie.org%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.tiny-vps.com%3a6969%2fannounce&tr=udp%3a%2f%2ftracker.cyberia.is%3a6969%2fannounce&tr=udp%3a%2f%2fopen.demonii.si%3a1337%2fannounce&tr=udp%3a%2f%2ftracker.nyaa.uk%3a6969%2fannounce&tr=udp%3a%2f%2fprestige.minimafia.nl%3a443%2fannounce&tr=udp%3a%2f%2fretracker.netbynet.ru%3a2710%2fannounce&tr=%2fannounce&tr=udp%3a%2f%2fhk1.opentracker.ga%3a6969%2fannounce&tr=udp%3a%2f%2fipv4.tracker.harry.lu%3a80";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stream_given);

    streamUrl = getIntent().getExtras().getString("url");
    Log.d(TORRENT, "link :- " + streamUrl);

    String action = getIntent().getAction();
    Uri data = getIntent().getData();
    if (action != null && action.equals(Intent.ACTION_VIEW) && data != null) {
      try {
        streamUrl = URLDecoder.decode(data.toString(), "utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    progressBar = findViewById(R.id.progressBar);
    prgrs = findViewById(R.id.prrogress);

    TorrentOptions torrentOptions = new TorrentOptions.Builder()
      .saveLocation(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
      .removeFilesAfterStop(true)
      .build();

    torrentStream = TorrentStream.init(torrentOptions);
    torrentStream.addListener(this);

    prgrs.setText("0 %");
    progressBar.setProgress(0);
/*    if(torrentStream.isStreaming()) {
      torrentStream.stopStream();
      return;
    }*/
    torrentStream.startStream(streamUrl);

    progressBar.setMax(100);

  }

  @Override
  protected void onResume() {
    super.onResume();

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }
  }

  @Override
  public void onStreamPrepared(Torrent torrent) {
    Log.d(TORRENT, "OnStreamPrepared");
    // If you set TorrentOptions#autoDownload(false) then this is probably the place to call
    // torrent.startDownload();
  }

  @Override
  public void onStreamStarted(Torrent torrent) {
    Log.d(TORRENT, "onStreamStarted");
  }

  @Override
  public void onStreamError(Torrent torrent, Exception e) {
    Log.e(TORRENT, "onStreamError", e);
  }

  @Override
  public void onStreamReady(Torrent torrent) {
    progressBar.setProgress(100);
    Log.d(TORRENT, "onStreamReady: " + torrent.getVideoFile());

    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(torrent.getVideoFile().toString()));
    intent.setDataAndType(Uri.parse(torrent.getVideoFile().toString()), "video/mp4");
    startActivity(intent);
  }

  @Override
  public void onStreamProgress(Torrent torrent, StreamStatus status) {
    if(status.bufferProgress <= 100 && progressBar.getProgress() < 100 && progressBar.getProgress() != status.bufferProgress) {
      Log.d(TORRENT, "Progress: " + status.bufferProgress);
      prgrs.setText(status.bufferProgress +" %");
      progressBar.setProgress(status.bufferProgress);
    }
  }

  @Override
  public void onStreamStopped() {
    Log.d(TORRENT, "onStreamStopped");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }



}
