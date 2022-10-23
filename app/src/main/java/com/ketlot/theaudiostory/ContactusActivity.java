/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;


public class ContactusActivity extends  AppCompatActivity  {


	private final Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.contactus);
		initialize();
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 1000);
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

		findViewById(R.id.linear3);
		findViewById(R.id.linear2);
		findViewById(R.id.linear4);
		findViewById(R.id.linear5);
		ImageView imgintsa = (ImageView) findViewById(R.id.imgintsa);
		TextView instatext = (TextView) findViewById(R.id.instatext);
		ImageView imgemail = (ImageView) findViewById(R.id.imgemail);
		TextView email = (TextView) findViewById(R.id.email);
		
		imgintsa.setOnClickListener(_view -> {
			i.setData(Uri.parse("https://www.instagram.com/thesmartaniket/"));
			i.setAction(Intent.ACTION_VIEW);
			startActivity(i);
		});
		
		instatext.setOnClickListener(_view -> {
			i.setData(Uri.parse("https://www.instagram.com/thesmartaniket/"));
			i.setAction(Intent.ACTION_VIEW);
			startActivity(i);
		});
		
		imgemail.setOnClickListener(_view -> {
			i.setData(Uri.parse("mailto:contact.aniket.biswas@gmail.com"));
			i.setAction(Intent.ACTION_CALL);
			startActivity(i);
		});
		
		email.setOnClickListener(_view -> {
			i.setData(Uri.parse("mailto:contact.aniket.biswas@gmail.com"));
			i.setAction(Intent.ACTION_VIEW);
			startActivity(i);
		});
	}
	
	private void initializeLogic() {
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
