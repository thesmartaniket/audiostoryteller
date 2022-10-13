/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class UpdateActivity extends  AppCompatActivity  { 
	
	private final Timer _timer = new Timer();
	private final FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String your_version = "";
	private String latest_version = "";

	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();

	private TextView textview2;
	private TextView textview4;
	private TextView textview3;

	private final Intent i = new Intent();
	private final DatabaseReference Ver = _firebase.getReference("version");
	private TimerTask t;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.update);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		LinearLayout linear1 = findViewById(R.id.linear1);
		LinearLayout linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		textview4 = findViewById(R.id.textview4);
		textview3 = findViewById(R.id.textview3);
		TextView textview1 = findViewById(R.id.textview1);
		AlertDialog.Builder d = new AlertDialog.Builder(this);

		ChildEventListener _Ver_child_listener = new ChildEventListener() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
				};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				textview2.setText("Checking for updates...");
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
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(() -> {
										SketchwareUtil.showMessage(getApplicationContext(), "Updates available");
										textview2.setText("Updates available...");
										i.setAction(Intent.ACTION_VIEW);
										i.setData(Uri.parse("https://audio-story-teller.en.uptodown.com/android/download"));
										startActivity(i);
									});
								}
							};
							_timer.schedule(t, 1000);
						} else {
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(() -> textview2.setText("No updates available"));
								}
							};
							_timer.schedule(t, 1000);
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
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);

			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot _param1, String _param2) {

			}

			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
				};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);

			}

			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();

			}
		};
		Ver.addChildEventListener(_Ver_child_listener);
	}
	
	private void initializeLogic() {
		textview3.setText("How to Update?\n\n1. Download the apk from uptodown website when update available.\n\n2. Open \"Downloads\" folder through File Manager and search for \"audio-story-teller-x-x.apk\" or tap on the open in chrome notification.\n\n3. Install it and Done!");
		textview4.setText("App Version - V.3.3.0.s\nBuild Version - #2022.1001.3.3.s\nCode Version - 12.0.3\n\nThis is a developer version\n1. Improved Stability.\n2. Fixed bugs and random crashes.\n3. Fixed glitching issues. \n4. More consistent UI design(in Contact Us, Downloads & Buttons).\n5. Build Update for October 2022.\n6. Based on new 12.0.3 code Version.");
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
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

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
