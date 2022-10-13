/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class SettingsActivity extends  AppCompatActivity  { 
	
	
	private final String package_name = "";
	private final HashMap<String, Object> map = new HashMap<>();
	private final String your_version = "";
	private final String latest_version = "";
	
	private final ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();

	private final Intent i = new Intent();

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		LinearLayout linear1 = findViewById(R.id.linear1);
		LinearLayout linear8 = findViewById(R.id.linear8);
		LinearLayout linear2 = findViewById(R.id.linear2);
		LinearLayout linear3 = findViewById(R.id.linear3);
		LinearLayout linear4 = findViewById(R.id.linear4);
		LinearLayout linear5 = findViewById(R.id.linear5);
		LinearLayout linear7 = findViewById(R.id.linear7);
		TextView textview10 = findViewById(R.id.textview10);
		TextView textview9 = findViewById(R.id.textview9);
		Button button1 = findViewById(R.id.button1);
		ImageView imgdownloads = findViewById(R.id.imgdownloads);
		TextView downloads = findViewById(R.id.downloads);
		ImageView imgprivacy = findViewById(R.id.imgprivacy);
		TextView privacy_policy = findViewById(R.id.privacy_policy);
		ImageView imgcontact = findViewById(R.id.imgcontact);
		TextView contact_us = findViewById(R.id.contact_us);
		ImageView imgabout = findViewById(R.id.imgabout);
		TextView about_us = findViewById(R.id.about_us);
		ImageView imgupdate = findViewById(R.id.imgupdate);
		TextView update = findViewById(R.id.update);
		FirebaseAuth fauth = FirebaseAuth.getInstance();
		
		button1.setOnClickListener(_view -> {
			FirebaseAuth.getInstance().signOut();
			finishAffinity();
		});
		
		imgdownloads.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), DownloadsActivity.class);
			startActivity(i);
		});
		
		downloads.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), DownloadsActivity.class);
			startActivity(i);
		});
		
		imgprivacy.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), PrivacyActivity.class);
			startActivity(i);
		});
		
		privacy_policy.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), PrivacyActivity.class);
			startActivity(i);
		});
		
		imgcontact.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), ContactusActivity.class);
			startActivity(i);
		});
		
		contact_us.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), ContactusActivity.class);
			startActivity(i);
		});
		
		imgabout.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), AboutActivity.class);
			startActivity(i);
		});
		
		about_us.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), AboutActivity.class);
			startActivity(i);
		});
		
		imgupdate.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), UpdateActivity.class);
			startActivity(i);
		});
		
		update.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), UpdateActivity.class);
			startActivity(i);
		});

		OnCompleteListener<Void> fauth_updateEmailListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<Void> fauth_updatePasswordListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<Void> fauth_emailVerificationSentListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<Void> fauth_deleteUserListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<AuthResult> fauth_phoneAuthListener = task -> {
			final boolean _success = task.isSuccessful();
			final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";

		};

		OnCompleteListener<Void> fauth_updateProfileListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<AuthResult> fauth_googleSignInListener = task -> {
			final boolean _success = task.isSuccessful();
			final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";

		};

		OnCompleteListener<AuthResult> _fauth_create_user_listener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<AuthResult> _fauth_sign_in_listener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<Void> _fauth_reset_password_listener = _param1 -> {
			final boolean _success = _param1.isSuccessful();

		};
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
