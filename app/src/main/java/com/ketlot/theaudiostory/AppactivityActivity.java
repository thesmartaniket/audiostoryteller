/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AppactivityActivity extends  AppCompatActivity  { 
	
	private final Timer _timer = new Timer();
	private final FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

	private String your_version = "";
	private String latest_version = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();

	private ProgressBar progressbar1;
	private WebView webview1;

	private final DatabaseReference Ver = _firebase.getReference("version");
	private AlertDialog.Builder d;
	private final Intent i = new Intent();
	private TimerTask timer;
	private final Intent k = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.appactivity);
		initialize();
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initialize() {

		findViewById(R.id.linear2);
		progressbar1 = findViewById(R.id.progressbar1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		ImageView share = findViewById(R.id.share);
		ImageView settings = findViewById(R.id.settings);
		d = new AlertDialog.Builder(this);
		FirebaseAuth.getInstance();
		new RequestNetwork(this);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				progressbar1.setVisibility(View.VISIBLE);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(() -> progressbar1.setProgress(webview1.getProgress()));
					}
				};
				_timer.scheduleAtFixedRate(timer, 100, 100);
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				progressbar1.setVisibility(View.GONE);
				timer.cancel();
				super.onPageFinished(_param1, _param2);
			}
		});
		
		share.setOnClickListener(_view -> {
			d.setTitle("Share a Story");
			d.setMessage("Share your story to stream it in our streaming platform. By clicking \"Upload\" you will be redirected to upload a story.");
			d.setPositiveButton("Back", (_dialog, _which) -> {

			});
			d.setNegativeButton("Upload", (_dialog, _which) -> {
				k.setData(Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdCU6PqGqzDhJqAmH5bDA6_MnMt1EkZXaQrIdqvxKePthbchA/viewform?usp=sf_link"));
				k.setAction(Intent.ACTION_VIEW);
				startActivity(k);
			});
			d.create().show();
		});
		
		settings.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), SettingsActivity.class);
			startActivity(i);
		});

		ChildEventListener _Ver_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
				};
				_param1.getKey();
				_param1.getValue(_ind);
				Ver.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot _dataSnapshot) {
						map1 = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
							};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								map1.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						latest_version = Objects.requireNonNull(map1.get(0).get("v")).toString();
						if (Double.parseDouble(latest_version) > Double.parseDouble(your_version)) {
							d.setTitle("Update Available");
							d.setMessage("A new update is available. Users are recommended to update to latest version.");
							d.setPositiveButton("Update", (_dialog, _which) -> {
								i.setAction(Intent.ACTION_VIEW);
								i.setData(Uri.parse("https://audio-story-teller.en.uptodown.com/android/download"));
								startActivity(i);
							});
							d.setNegativeButton("Later", (_dialog, _which) -> {

							});
							d.create().show();
						}
					}

					@Override
					public void onCancelled(@NonNull DatabaseError _databaseError) {
					}
				});
			}

			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
				};
				_param1.getKey();
				_param1.getValue(_ind);

			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot _param1, String _param2) {

			}

			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
				};
				_param1.getKey();
				_param1.getValue(_ind);

			}

			@Override
			public void onCancelled(DatabaseError _param1) {
				_param1.getCode();
				_param1.getMessage();

			}
		};
		Ver.addChildEventListener(_Ver_child_listener);

	}
	
	private void initializeLogic() {
		String package_name = "com.ketlot.theaudiostory";
		try {
			android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo(package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
			your_version = pinfo.versionName; }
		catch (Exception e){ showMessage(e.toString()); }
		DatabaseReference rootRef = _firebase.getReference(); rootRef.child("version").addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				if (snapshot.exists()) { } else {
					map = new HashMap<>();
					map.put("v", your_version);
					Ver.child("app").updateChildren(map);
					map.clear();
				} }
			@Override
			public void onCancelled(@NonNull DatabaseError _error) { } });
		_download_path("/music/audiostoryteller/");
		webview1.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				try {
					DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
					String cookies = CookieManager.getInstance().getCookie(url);
					request.addRequestHeader("cookie", cookies);
					request.addRequestHeader("User-Agent", userAgent);
					request.setDescription("Downloading file...");
					request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
					request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					java.io.File aatv = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/music/audiostoryteller/");
					if(!aatv.exists()){if (!aatv.mkdirs()){ Log.e("TravellerLog ::","Problem creating Image folder");}}
					
					request.setDestinationInExternalPublicDir("/music/audiostoryteller/", URLUtil.guessFileName(url, contentDisposition, mimetype));
					DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
					manager.enqueue(request);
					showMessage("Downloading File...");
					
					//Notif if success
					BroadcastReceiver onComplete = new BroadcastReceiver() {
						public void onReceive(Context ctxt, Intent intent) {
							showMessage("Download Completed");
							unregisterReceiver(this);
						}};
					registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
				} catch (Exception e){
					showMessage(e.toString());
				}
			}
			
		});
		webview1.loadUrl("https://theaudiostorybeta.blogspot.com/?m=1");
		webview1.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		progressbar1.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Override
	public void onBackPressed() {
		if (webview1.canGoBack()) {
			webview1.goBack();
			
		}
		else {
			d.setTitle("Exit");
			d.setMessage("Do you want to exit the app?");
			d.setPositiveButton("Yes", (_dialog, _which) -> finishAffinity());
			d.setNegativeButton("No", (_dialog, _which) -> {

			});
			d.create().show();
		}
	}
	public void _download_path (final String _file_path) {
		if (FileUtil.isExistFile(_file_path)) {
			
		}
		else {
			FileUtil.makeDir(_file_path);
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@SuppressLint("SuspiciousIndentation")
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
