package com.huayin.smack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

/**
 * @ClassName:  AudioFileTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     wangjinyong
 * @date:       2017年8月2日 下午2:28:24
 * Copyright (c) 2017, Huayin. All rights reserved
 */
public class AudioFileTest {
	//获取网络MP3文件播放时长(s)
	public static int getNetMp3TrackLength(URL urlfile) throws IOException, BitstreamException{
		URLConnection con = null;
        try {
            con = urlfile.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int b = con.getContentLength();//
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
        Bitstream bt = new Bitstream(bis);
        Header h = bt.readFrame();
        int time = (int) h.total_ms(b);
        return time/1000;
	}
	
	//获取本地MP3文件播放时长(s)
	public static int getMp3TrackLength(File mp3File) {  
	    try {  
	        MP3File f = (MP3File) AudioFileIO.read(mp3File);  
	        MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();  
	        return audioHeader.getTrackLength();  
	    } catch(Exception e) {  
	        return -1;
	    }  
	}

	/** 
	 * @Title:        main 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param args    
	 * @return:       void    
	 * @throws 
	 * @author        wangjinyong
	 * @Date          2017年8月2日 下午2:28:24 
	 */
	public static void main(String[] args) {
		File file = new File("D:\\pgData\\136921031606936.mp3");
		int play_duration = getMp3TrackLength(file);
		System.out.println("play_duration = "+play_duration);
		
		URL urlfile;
		try {
			urlfile = new URL("http://sc1.111ttt.com/2015/1/06/06/99060941326.mp3");
			int net_duration = getNetMp3TrackLength(urlfile);
			System.out.println("net_duration = "+net_duration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
