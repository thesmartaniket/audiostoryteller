/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class PrivacyActivity extends  AppCompatActivity  {


	private TextView privacytext;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.privacy);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		LinearLayout linear1 = findViewById(R.id.linear1);
		LinearLayout linear2 = findViewById(R.id.linear2);
		ScrollView vscroll1 = findViewById(R.id.vscroll1);
		TextView textview1 = findViewById(R.id.textview1);
		privacytext = findViewById(R.id.privacytext);
	}
	
	private void initializeLogic() {
		privacytext.setText("At \"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\", accessible from \"https://theaudiostory.blogspot.com/\", one of our main priorities is the privacy of our visitors. This Privacy Policy document contains types of information that is collected and recorded by The Audio Story Teller and how we use it.\n\nIf you have additional questions or require more information about our Privacy Policy, do not hesitate to contact us.\n\nThis Privacy Policy applies only to our online activities and is valid for visitors to our website with regards to the information that they shared and/or collect in \"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\". This policy is not applicable to any information collected offline or via channels other than this website.\n\nConsent\n\nBy using our website, you hereby consent to our Privacy Policy and agree to its terms.\n\nA) Information we collect\n\nThe personal information that you are asked to provide, and the reasons why you are asked to provide it, will be made clear to you at the point we ask you to provide your personal information. Though we never asked our users to share any personal information rather than their email address in our website while using it. \n\nIf you contact us directly, we may receive additional information about you such as your name, email address, phone number, the contents of the message and/or attachments you may send us, and any other information you may choose to provide.\n\nWhen you register for an Account, we may ask for your contact information, including items such as name, company name, address, email address, and telephone number. All the registration/sign up or login/sign in process are handled by \"Google Firebase\".\n\nB) How we use your information\n\nWe use the information we collect in various ways, including to:\n\n1.Provide, operate, and maintain our website.\n\n2.Improve, personalise, and expand our website.\n\n3.Understand and analyse how you use our website.\n\n5.Develop services, features, and functionality.\n\n6.Communicate with you, either directly or through social medias or emails, to provide you with updates and other information relating to the website, and for marketing and promotional purposes.\n\n7.Sends you emails for updating you about latest posts after subscribing us. \n\n8.Find and prevent fraud.\n\nC) Log Files\n\n\"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\" follows a standard procedure of using log files. These files log visitors when they visit websites. All hosting companies do this and a part of hosting services' analytics. The information collected by log files include internet protocol (IP) addresses, browser type, Internet Service Provider (ISP), date and time stamp, referring/exit pages, and possibly the number of clicks. These are not linked to any information that is personally identifiable. The purpose of the information is for analysing trends, administering the site, tracking users' movement on the website, and gathering demographic information.\n\nD) Cookies and Web Beacons\n\nLike any other website, \"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\"r uses 'cookies'. These cookies are used to store information including visitors' preferences, and the pages on the website that the visitor accessed or visited. The information is used to optimise the users' experience by customising our web page content based on visitors' browser type and/or other information.\n\nFor more general information on cookies, please read \"What Are Cookies\".\n\nOur Advertising Partners\n\nSome of advertisers on our site may use cookies and web beacons. Our advertising partners are listed below. Each of our advertising partners has their own Privacy Policy for their policies on user data. For easier access, we hyperlinked to their Privacy Policies below.\n\n1.Amazon (Privacy Policy)\n\n2.Google (Privacy Policy)\n\nAdvertising Partners Privacy Policies\n\nYou may consult this list to find the Privacy Policy for each of the advertising partners of \"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\".\n\nThird-party ad servers or ad networks uses technologies like cookies, JavaScript, or Web Beacons that are used in their respective advertisements and links that appear on \"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\", which are sent directly to users' browser. They automatically receive your IP address when this occurs. These technologies are used to measure the effectiveness of their advertising campaigns and/or to personalise the advertising content that you see on websites that you visit.\n\nNote that \"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\" has no access to or control over these cookies that are used by third-party advertisers.\n\nE) Story Uploading Policy\n\nWhen a user uploads an audio story by filling the form, we asks for information related to the audio story. We use those information to verify that the uploaded audio story is a genuine, own made instead of being copy or stolen one. We also get the audio file that the user uploaded and we use to make it available on our streaming services. We don't sell those properties(Audio Story) to other services or partners.\n\nF) Third Party Privacy Policies\n\n(\"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\")'s Privacy Policy does not apply to other advertisers or websites. Thus, we are advising you to consult the respective Privacy Policies of these third-party ad servers for more detailed information. It may include their practices and instructions about how to opt-out of certain options.\n\nYou can choose to disable cookies through your individual browser options. To know more detailed information about cookie management with specific web browsers, it can be found at the browsers' respective websites.\n\nCCPA Privacy Rights (Do Not Sell My Personal Information)\n\nUnder the CCPA, among other rights, California(Officially available only for India) consumers have the right to:\n\nRequest that a business that collects a consumer's personal data disclose the categories and specific pieces of personal data that a business has collected about consumers.\n\nRequest that a business delete any personal data about the consumer that a business has collected.\n\nRequest that a business that sells a consumer's personal data, not sell the consumer's personal data.\n\nIf you make a request, we have one month to respond to you. If you would like to exercise any of these rights, please contact us.\n\nG) GDPR Data Protection Rights\n\nWe would like to make sure you are fully aware of all of your data protection rights. Every user is entitled to the following:\n\nThe right to access – You have the right to request copies of your personal data. We may charge you a small fee for this service.\n\nThe right to rectification – You have the right to request that we correct any information you believe is inaccurate. You also have the right to request that we complete the information you believe is incomplete.\n\nThe right to erasure – You have the right to request that we erase your personal data, under certain conditions.\n\nThe right to restrict processing – You have the right to request that we restrict the processing of your personal data, under certain conditions.\n\nThe right to object to processing – You have the right to object to our processing of your personal data, under certain conditions.\n\nThe right to data portability – You have the right to request that we transfer the data that we have collected to another organisation, or directly to you, under certain conditions.\n\nIf you make a request, we have one month to respond to you. If you would like to exercise any of these rights, please contact us.\n\nH) Children's Information\n\nAnother part of our priority is adding protection for children while using the internet. We encourage parents and guardians to observe, participate in, and/or monitor and guide their online activity.\n\n\"The Audio Story Teller\" or \"The Audio Story\" or \"Audio Story Teller\" or \"App Services(Windows/Android)\" does not knowingly collect any Personal Identifiable Information from children under the age of 13. If you think that your child provided this kind of information on our website, we strongly encourage you to contact us immediately and we will do our best efforts to promptly remove such information from our records.\n\n\nPrivacy Policy may change anytime without any notice. Users are requested to keep an eye on our social media handles for notifications.\n\n");
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
