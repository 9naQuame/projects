package com.example.lurvberry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.ImageView;

public class Header extends Activity {
	protected TextView title;
	protected ImageView icon;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.content);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
        
        title = (TextView) findViewById(R.id.title);
        icon  = (ImageView) findViewById(R.id.icon);
	}
}