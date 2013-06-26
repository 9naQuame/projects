package com.example.lurvberry;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	TabHost tabHost;
	TextView title;
	ImageView icon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
		
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		title = (TextView) findViewById(R.id.title);
        icon  = (ImageView) findViewById(R.id.icon);

		tabHost.setup();
		
		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener(){
			
			@Override
			public void onTabChanged(String tabId) {
				FragmentManager tab =   getSupportFragmentManager();
				PlayerFragment player = (PlayerFragment) tab.findFragmentByTag("player");
				SongFragment songs = (SongFragment) tab.findFragmentByTag("songs");
				SettingFragment settings = (SettingFragment) tab.findFragmentByTag("setting");
				
				FragmentTransaction tabChange = tab.beginTransaction();
				
				if(player != null) tabChange.detach(player);
				if(songs != null) tabChange.detach(songs);
				if(settings != null) tabChange.detach(settings);
				
				if(tabId.equalsIgnoreCase("player")){
					if(player == null) tabChange.add(R.id.selectedTabcontent,new PlayerFragment(), "player");						
					else tabChange.attach(player);		
					title.setText("Player");
				}
				
				else if(tabId.equalsIgnoreCase("songs")){
					if(songs == null) tabChange.add(R.id.selectedTabcontent,new SongFragment(), "songs");						
					else tabChange.attach(songs);
					title.setText("Songs");
				}
				
				else{
					if(settings == null) tabChange.add(R.id.selectedTabcontent,new SettingFragment(), "setting");						
					else tabChange.attach(settings);	
					title.setText("Settings");
				}
					
				tabChange.commit();		
		}
	};
	
	/** Setting tabchangelistener for the tab */
	tabHost.setOnTabChangedListener(tabChangeListener);
            
	/** Defining tab builder for Player tab */
    TabHost.TabSpec tSpecPlayer = tabHost.newTabSpec("player");
    tSpecPlayer.setIndicator("Player",getResources().getDrawable(R.drawable.android));        
    tSpecPlayer.setContent(new TabContent(getBaseContext()));
    tabHost.addTab(tSpecPlayer);
    
    /** Defining tab builder for Songs tab */
    TabHost.TabSpec tSpecSong = tabHost.newTabSpec("songs");
    tSpecSong.setIndicator("Songs",getResources().getDrawable(R.drawable.apple));        
    tSpecSong.setContent(new TabContent(getBaseContext()));
    tabHost.addTab(tSpecSong);
    
    /** Defining tab builder for Settings tab */
    TabHost.TabSpec tSpecSetting = tabHost.newTabSpec("setting");
    tSpecSetting.setIndicator("Settings",getResources().getDrawable(R.drawable.apple));        
    tSpecSetting.setContent(new TabContent(getBaseContext()));
    tabHost.addTab(tSpecSetting);
    
 }   

}
