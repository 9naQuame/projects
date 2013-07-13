package com.example.lurvberry;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CallIntentService extends IntentService{

	public CallIntentService() {
		super("CallIntentService");
		// TODO Auto-generated constructor stub
		
		Log.d("lurvberry","Callintentservice constructor");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
		Log.d("lurvberry", "onHandle intent called");
		
		Intent in= new Intent();
		in.setAction("sohail.aziz");
		Log.d("lurvberry", "onHandleIntent: sending broadcast");
		sendBroadcast(in);
		
		
	}

}
