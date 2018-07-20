package com.wkb.chat.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

public class BaiDuAi {
	
	public static final String APP_ID = "";
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";
    public static final String CLASSPATH = FileUtil.getUplodFilePath();
    
    /**
     * 语言合成
     * @param text 文本内容
     * @param sex 设置性别(0为男，1为女)
     * @return
     */
    public static boolean SoundSynthesis(String text,String sex,long fielName) {
    	
    	AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
    	client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        if(sex==null) {
        	sex = "1";
        }
        
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", sex);

        // 调用接口
        TtsResponse res = client.synthesis(text, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, CLASSPATH+"upload/"+fielName+".wav");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            try {
				System.out.println(res1.toString(2));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	return true;
	}
    
    /**
     * 语音识别
     * @param args
     * @throws JSONException
     */
    
    public static JSONObject soundDistinguish(String filePath) throws JSONException {

        // 初始化一个FaceClient
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        byte[] b = getBytes(filePath);
        
        // 调用API
        JSONObject res = client.asr(b, "wav", 8000, null);
        return res;
    }
    
    
    public static byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    

}
