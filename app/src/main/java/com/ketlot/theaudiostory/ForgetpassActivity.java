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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Random;


public class ForgetpassActivity extends  AppCompatActivity  {


	private EditText email;

	private FirebaseAuth fauth;
	private OnCompleteListener<Void> _fauth_reset_password_listener;
	private final Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.forgetpass);
		initialize();
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize() {

		findViewById(R.id.linear2);
		findViewById(R.id.textview1);
		email = findViewById(R.id.email);
		Button forgetpassbutt = findViewById(R.id.forgetpassbutt);
		findViewById(R.id.linear3);
		TextView signinbutt = findViewById(R.id.signinbutt);
		TextView signupbutt = findViewById(R.id.signupbutt);
		fauth = FirebaseAuth.getInstance();
		
		forgetpassbutt.setOnClickListener(_view -> {
			if (email.getText().toString().equals("")) {
				SketchwareUtil.showMessage(getApplicationContext(), "Field is empty!");
			}
			else {
				fauth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(_fauth_reset_password_listener);
			}
		});
		
		signinbutt.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), MainActivity.class);
			startActivity(i);
		});
		
		signupbutt.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), SignupActivity.class);
			startActivity(i);
		});

		_fauth_reset_password_listener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			if (_success) {
				finish();
			}
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
