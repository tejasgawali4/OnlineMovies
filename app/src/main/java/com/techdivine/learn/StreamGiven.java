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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stream_given);

    String streamUrl = getIntent().getExtras().getString("url");
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
