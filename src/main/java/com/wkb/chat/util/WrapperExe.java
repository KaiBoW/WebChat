package com.wkb.chat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrapperExe {
	
	private static final Logger log = LoggerFactory.getLogger(WrapperExe.class);
	public static final String CLASSPATH = FileUtil.getUplodFilePath();
	
	public boolean mergeFile(String videoFilePath,String armFilePath,long fileName) {  
		  
        //合并文件  
        //头一个file为amr文件  
        try {  
            log.info("Begin to merge video file " + videoFilePath + " into " + armFilePath);  
              
  
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();  
            if(classLoader == null) {  
                classLoader = ClassLoader.getSystemClassLoader();  
            }  
  
            System.out.println(new File(classLoader.getResource("").toURI()).getParentFile());
            
            String command = CLASSPATH + "\\ffmpeg\\bin\\ffmpeg -i " + armFilePath + " -i " + videoFilePath + " "+ CLASSPATH + "upload\\"+fileName+".mp4 -loglevel quiet -y";  
            System.out.println(command);  
            
            log.info("The command of ffmpeg is " + command);  
              
            Process process =Runtime.getRuntime().exec(command);  
              
            final InputStream in = process.getInputStream();  
            final InputStream error = process.getErrorStream();  
              
  
            new Thread(){  
                public void run() {  
                    BufferedReader br = new BufferedReader(new InputStreamReader(error));  
                      
                    try {  
                        while(br.readLine() != null) {  
                            continue;  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    } finally {  
                        try {  
                            br.close();  
                            in.close();  
                        } catch (IOException e) {  
                            e.printStackTrace();  
                        }  
                          
                    }  
                };  
            }.start();  
              
            //waitFor()操作阻塞线程，等待process执行结束  
            process.waitFor();  
  
            process.destroy();  
              
            /*log.info("Success to execute " + command);  
            log.info("Success to merge " + videoFilePath + " into " + armFilePath + ", and success to create " +  videoFilePath + "/_" + "D:\\worespace\\WebChat\\upload\\outputVideo.mp4");  
              */
              
        } catch (Exception e) {  
              
            log.error("Exception occurs when merging video file", e);  
            return false;  
        }  
          
        return true;  
    } 
	
	public static void main(String[] args) {
		/*WrapperExe w = new WrapperExe();
		w.mergeFile("D:\\worespace\\WebChat\\upload\\test.mp4", "D:\\worespace\\WebChat\\upload\\test.ogg");*/
		System.out.println(new Date().getTime() + ".mp4");
		
	}
	 
	 
} 
