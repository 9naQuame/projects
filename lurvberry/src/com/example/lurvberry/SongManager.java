package com.example.lurvberry;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SongManager {
	// SDCard Path
	final String MEDIA_PATH = "/sdcard/external_sd/Music";
	private ArrayList<LinkedHashMap<String, String>> songsList = new ArrayList<LinkedHashMap<String, String>>();
	
	// Constructor
	public SongManager(){
		
	}
	
	/* Function to read all mp3 files from sdcard and store the details in ArrayList */
	public ArrayList<LinkedHashMap<String, String>> getSongs(){
		File home = new File(MEDIA_PATH);

		if (home.listFiles(new FileExtensionFilter()).length > 0) {
			for (File file : home.listFiles(new FileExtensionFilter())) {
				LinkedHashMap<String, String> song = new LinkedHashMap<String, String>();
				song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
				song.put("songPath", file.getPath());
				
				songsList.add(song); // Adding each song to SongList
			}
		}
		return songsList; // return songs list array
	}
	
	/* Class to filter files which are having .mp3 extension */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}
