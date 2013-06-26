package com.example.lurvberry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerFragment extends Fragment {
	
	private ImageButton playButton;
	private ImageButton forwardButton;
	private ImageButton rewindButton;
	private ImageButton nextButton;
	private ImageButton previousButton;
	private ImageButton playlistButton;
	private ImageButton repeatButton;
	private ImageButton shuffleButton;
	private SeekBar songProgressBar;
	private TextView songTitleLabel;
	private TextView songCurrentDurationLabel;
	private TextView songTotalDurationLabel;

	private MediaPlayer player;
	
	private Handler PlayerHandler = new Handler();
	private SongManager songManager;
	private Utilities utils;
	private int seekForwardTime = 5000;
	private int seekRewindTime = 5000;
	private int currentSongIndex = 0; 
	private boolean isShuffle = false;
	private boolean isRepeat = false;
	private ArrayList<LinkedHashMap<String, String>> songsList = new ArrayList<LinkedHashMap<String, String>>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.player, container, false);
        
        // All player buttons
     	playButton = (ImageButton) view.findViewById(R.id.btnPlay);
     	forwardButton = (ImageButton) view.findViewById(R.id.btnForward);
     	rewindButton = (ImageButton) view.findViewById(R.id.btnBackward);
     	nextButton = (ImageButton) view.findViewById(R.id.btnNext);
     	previousButton = (ImageButton) view.findViewById(R.id.btnPrevious);
     	playlistButton = (ImageButton) view.findViewById(R.id.btnPlaylist);
     	repeatButton = (ImageButton) view.findViewById(R.id.btnRepeat);
     	shuffleButton = (ImageButton) view.findViewById(R.id.btnShuffle);
     	songProgressBar = (SeekBar) view.findViewById(R.id.songProgressBar);
     	songTitleLabel = (TextView) view.findViewById(R.id.songTitle);
     	songCurrentDurationLabel = (TextView) view.findViewById(R.id.songCurrentDurationLabel);
     	songTotalDurationLabel = (TextView) view.findViewById(R.id.songTotalDurationLabel);
        
     	player = new MediaPlayer();
     	songManager = new SongManager();
		utils = new Utilities();
		
		//Listeners Very Important
		//songProgressBar.setOnSeekBarChangeListener(this); 
		//player.setOnCompletionListener(this);
		
		songsList = songManager.getSongs();
		playSong(0);
		
		/* Play button click event */
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(player.isPlaying()){
					if(player!=null){
						player.pause();
						playButton.setImageResource(R.drawable.btn_play);
					}
				}
				else{
					if(player!=null){
						player.start();
						playButton.setImageResource(R.drawable.btn_pause);
					}
				}
			}
		});
		
		/* Forward button click event */
		forwardButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int currentPosition = player.getCurrentPosition();
				if(currentPosition + seekForwardTime <= player.getDuration()) 
					player.seekTo(currentPosition + seekForwardTime);
				else player.seekTo(player.getDuration());
			}
		});
		
		/* Rewind button click event */
		rewindButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int currentPosition = player.getCurrentPosition();
				if(currentPosition - seekRewindTime >= 0)
					player.seekTo(currentPosition - seekRewindTime);
				else player.seekTo(0);
			}
		});
		
     	return view;
	}
	
	/* Function to play a song */
	public void  playSong(int songIndex){
		try {
        	player.reset();
        	player.setDataSource(songsList.get(songIndex).get("songPath"));
        	player.prepare();
        	player.start();
 
        	String songTitle = songsList.get(songIndex).get("songTitle");
        	songTitleLabel.setText(songTitle);
			
			playButton.setImageResource(R.drawable.btn_pause);
			
			// set Progress bar values
			songProgressBar.setProgress(0);
			songProgressBar.setMax(100);
			
			// Updating progress bar
			//updateProgressBar();			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}