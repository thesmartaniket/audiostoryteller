/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class DownloadsActivity extends  AppCompatActivity  { 
	
	private final Timer _timer = new Timer();
	
	private double songPosition = 0;

	private final ArrayList<String> musics = new ArrayList<>();
	private final ArrayList<HashMap<String, Object>> allmusic = new ArrayList<>();

	private ListView listview1;
	private ImageView imageview1;
	private SeekBar seekbar1;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	
	private MediaPlayer mp;
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.downloads);
		initialize();
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
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
	
	private void initialize() {

		findViewById(R.id.linear1);
		listview1 = (ListView) findViewById(R.id.listview1);
		findViewById(R.id.linear4);
		findViewById(R.id.heading);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
		textview1 = (TextView) findViewById(R.id.textview1);
		findViewById(R.id.vscroll1);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		
		listview1.setOnItemClickListener((_param1, _param2, _param3, _param4) -> {
			if (songPosition == -1) {
				_MPcreate(_param3);
				_MPstart();
			}
			else {
				if (_param3 == songPosition) {
					if (mp.isPlaying()) {
						_MPpause();
					}
					else {
						_MPstart();
					}
				}
				else {
					if (mp.isPlaying()) {
						_MPpause();
					}
					mp.reset();
					mp.release();
					_MPcreate(_param3);
					_MPstart();
				}
			}
		});
		
		imageview1.setOnClickListener(_view -> {
			if (mp.isPlaying()) {
				_MPpause();
			}
			else {
				_MPstart();
			}
		});
		
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {

			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				if (!(songPosition == -1)) {
					mp.seekTo((int)(seekbar1.getProgress()));
				}
			}
		});
	}
	
	private void initializeLogic() {
		imageview1.setVisibility(View.GONE);
		songPosition = -1;
		FileUtil.listDir(FileUtil.getPublicDir(Environment.DIRECTORY_MUSIC).concat("/audiostoryteller/"), musics);
		double n = 0;
		for(int _repeat17 = 0; _repeat17 < (int)(musics.size()); _repeat17++) {
			if (musics.get((int)(n)).endsWith(".mp3")) {
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("song", musics.get((int)(n)));
					allmusic.add(_item);
				}
				
			}
			n++;
		}
		listview1.setAdapter(new Listview1Adapter(allmusic));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (songPosition == -1) {
			
		}
		else {
			if (mp.isPlaying()) {
				_MPpause();
			}
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	public void _MPcreate (final double _pos) {
		String currentfile = Objects.requireNonNull(allmusic.get((int) _pos).get("song")).toString();
		textview3.setText(Uri.parse(currentfile).getLastPathSegment());
		imageview1.setVisibility(View.VISIBLE);
		songPosition = _pos;
		mp = MediaPlayer.create(getApplicationContext(), Uri.fromFile(new java.io.File(currentfile)));
		seekbar1.setMax((int)mp.getDuration());
		textview2.setText(String.valueOf((long)((mp.getDuration() / 1000) / 60)).concat(":".concat(String.valueOf((long)((mp.getDuration() / 1000) % 60)))));
	}
	
	
	public void _MPstart () {
		mp.start();
		imageview1.setImageResource(R.drawable.pause);
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(() -> {
					seekbar1.setProgress((int)mp.getCurrentPosition());
					textview1.setText(String.valueOf((long)((mp.getCurrentPosition() / 1000) / 60)).concat(":".concat(String.valueOf((long)((mp.getCurrentPosition() / 1000) % 60)))));
				});
			}
		};
		_timer.scheduleAtFixedRate(timer, (int)(400), (int)(400));
	}
	
	
	public void _MPpause () {
		timer.cancel();
		mp.pause();
		imageview1.setImageResource(R.drawable.play);
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.mycustom, null);
			}

			_view.findViewById(R.id.linear1);
			_view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			textview1.setText(Uri.parse(Objects.requireNonNull(allmusic.get((int) _position).get("song")).toString()).getLastPathSegment());
			
			return _view;
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
