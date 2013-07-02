package com.example.lurvberry;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class SongFragment extends ListFragment{
	public ArrayList<LinkedHashMap<String, String>> songsList = new ArrayList<LinkedHashMap<String, String>>();
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.playlist, container, false);
        
        ArrayList<LinkedHashMap<String, String>> songsListData = new ArrayList<LinkedHashMap<String, String>>();
        
        SongManager player = new SongManager();
        
        this.songsList = player.getSongs();
        
        for (int i = 0; i < songsList.size(); i++) {
        	LinkedHashMap<String, String> song = songsList.get(i);
			songsListData.add(song);
		}
        
        ListAdapter adapter = new SimpleAdapter(getActivity(), songsListData,
				R.layout.playlist_item, new String[] { "songTitle" }, new int[] {
						R.id.songTitle });

		setListAdapter(adapter);
/*
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting listitem index
				int songIndex = position;
				
				// Starting new intent
				Intent in = new Intent(getActivity(),
						MainActivity.class);
				// Sending songIndex to PlayerActivity
				in.putExtra("songIndex", songIndex);
				setResult(100, in);
				// Closing PlayListView
				finish();
			}
		});*/
        
		return view;
	}
	
}