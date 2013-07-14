package com.example.lurvberry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ReceiverActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		Log.d("lurvberry","ReceiverAtivity :oncreate");
		Intent intnt=new Intent();
		//intnt.setAction("sohail.aziz.r2");
		Log.d("lurvberry","ReceiverAtivity :Sending Broadcast");
		sendBroadcast(intnt,"MYPERMISSION");
		
		
		
	}
	
	
}
