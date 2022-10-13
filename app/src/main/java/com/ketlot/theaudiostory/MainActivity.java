/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;


public class MainActivity extends  AppCompatActivity  { 
	
	private final FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

	private EditText email;
	private EditText password;

	private FirebaseAuth fauth;
	private OnCompleteListener<AuthResult> _fauth_sign_in_listener;
	private final Intent i = new Intent();
	private final DatabaseReference user = _firebase.getReference("users");

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
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
	
	private void initialize() {

		findViewById(R.id.linear2);
		findViewById(R.id.textview1);
		email = findViewById(R.id.email);
		password = findViewById(R.id.password);
		Button signinbutt = findViewById(R.id.signinbutt);
		findViewById(R.id.linear3);
		TextView signupbutt = findViewById(R.id.signupbutt);
		TextView forgetpassbutt = findViewById(R.id.forgetpassbutt);
		fauth = FirebaseAuth.getInstance();
		
		signinbutt.setOnClickListener(_view -> {
			if (email.getText().toString().equals("") && password.getText().toString().equals("")) {
				SketchwareUtil.showMessage(getApplicationContext(), "Fields are empty!");
			}
			else {
				fauth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(MainActivity.this, _fauth_sign_in_listener);
			}
		});
		
		signupbutt.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), SignupActivity.class);
			startActivity(i);
		});
		
		forgetpassbutt.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), ForgetpassActivity.class);
			startActivity(i);
		});

		ChildEventListener _user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
				};
				_param1.getKey();
				_param1.getValue(_ind);

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
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);

			}

			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();

			}
		};
		user.addChildEventListener(_user_child_listener);

		OnCompleteListener<Void> fauth_updateEmailListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<Void> fauth_updatePasswordListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<Void> fauth_deleteUserListener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

		};

		OnCompleteListener<AuthResult> _fauth_create_user_listener = Task::isSuccessful;
		
		_fauth_sign_in_listener = _param1 -> {
			final boolean _success = _param1.isSuccessful();
			final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
			if (_success) {
				i.setClass(getApplicationContext(), AppactivityActivity.class);
				startActivity(i);
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};

		OnCompleteListener<Void> _fauth_reset_password_listener = Task::isSuccessful;
	}
	
	private void initializeLogic() {
		_download_path("/musics/audiostoryteller/");
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			boolean emailVerified = Objects.requireNonNull(fauth.getCurrentUser()).isEmailVerified();
			if (emailVerified) {
				i.setClass(getApplicationContext(), AppactivityActivity.class);
				startActivity(i);
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "Verify your email");
			}
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

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
