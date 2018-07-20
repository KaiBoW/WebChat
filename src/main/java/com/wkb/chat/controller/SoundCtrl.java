package com.wkb.chat.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wkb.chat.util.BaiDuAi;
import com.wkb.chat.util.FileUtil;
import com.wkb.chat.util.ReturnJson;
import com.wkb.chat.util.TuLing;
import com.wkb.chat.util.WrapperExe;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javazoom.jl.decoder.JavaLayerException;


@RestController
@RequestMapping(value="SoundCtrl")
@Api(value="音频后台",tags="SoundCtrl")
public class SoundCtrl {
	
	public static final String CLASSPATH = FileUtil.getUplodFilePath();
	
	@ApiOperation(value="音频视频流上传")  
	@RequestMapping(value="SoundUpload",method=RequestMethod.POST)
	public void uploadSound(@RequestParam("videoData") MultipartFile videoData,@RequestParam("audioData") MultipartFile audioData,HttpServletResponse response) throws IOException, JavaLayerException {
		System.out.println(videoData.getName());
		System.out.println(audioData.getName());
		/**
		 * 判断文件是否存在，存在删除
		 */
		File videoFile = new File(CLASSPATH+"upload/test.mp4");
		if(videoFile.exists()) {
			videoFile.delete();
		}
		File audioFile = new File(CLASSPATH+"upload/test.ogg");
		if(audioFile.exists()) {
			audioFile.delete();
		}
		/**
		 * 将文件上传到指定文件夹
		 */
		File onlineVideoFile = new File(CLASSPATH+"upload/test.mp4");
		File onlineAudioFile = new File(CLASSPATH+"upload/test.ogg");
        try {
            FileCopyUtils.copy(videoData.getInputStream(), new FileOutputStream(onlineVideoFile));
            FileCopyUtils.copy(audioData.getInputStream(), new FileOutputStream(onlineAudioFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 音频视频合成
         */
        WrapperExe w = new WrapperExe();
        long fileName = new Date().getTime();
        boolean b = w.mergeFile(CLASSPATH+"upload/test.mp4", CLASSPATH+"upload/test.ogg",fileName);
        System.out.println(b);
		/*System.out.println(file);*/
		//ReturnJson.toJson(response,"upload/test.wav");
		/*System.out.println(number);*/
        ReturnJson.toJson(response,"upload/"+fileName+".mp4");
	}
	
	@ApiOperation(value="音频流上传")  
	@RequestMapping(value="armUpload",method=RequestMethod.POST)
	public void armUpload(@RequestParam("audioData") MultipartFile audioData,HttpServletResponse response) throws IOException, JavaLayerException, JSONException {
		
		long fileName = new Date().getTime();
		
		/**
		 * 将文件上传到指定文件夹
		 */
		File onlineAudioFile = new File(CLASSPATH+"upload/"+fileName+".wav");
        try {
            FileCopyUtils.copy(audioData.getInputStream(), new FileOutputStream(onlineAudioFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text;
        String resultText;
        /**
         * 将录音转为文字
         */
        JSONObject textJson = BaiDuAi.soundDistinguish(CLASSPATH+"upload/"+fileName+".wav");
        if(textJson.get("err_no").equals(0)) {
        	text = textJson.get("result").toString().substring(2,textJson.get("result").toString().length()-2);
        	/**
             * 调用图灵API
             */
        	resultText = TuLing.TuLingApi(text);
        }else {
        	resultText = "我听不清你在说什么";
        }
        /**
		 * 将文字转为音频文件
		 */
        BaiDuAi.SoundSynthesis(resultText, "0",fileName);
        
        
		ReturnJson.toJson(response,"upload/"+fileName+".wav");
		/*System.out.println(number);*/
	}
	
	
	
	
	
	
	
	
	@ApiOperation(value="语音合成")  
	@RequestMapping(value="SoundSynthesis",method=RequestMethod.POST)
	public void SoundSynthesis(String text,String sex,HttpServletResponse response) throws IOException {
		long fileName = new Date().getTime();
		BaiDuAi.SoundSynthesis(text, sex,fileName);
		response.sendRedirect("http://localhost:8080/upload/"+fileName+".wav");  
	}
	
	
	

}
