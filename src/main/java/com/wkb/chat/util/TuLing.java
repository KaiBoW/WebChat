package com.wkb.chat.util;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TuLing{
	
	public static String  APIKEY = ""; 
	
    public static String TuLingApi(String text) throws IOException { 
        //String INFO = URLEncoder.encode("北京今日天气", "utf-8"); 
        String INFO = URLEncoder.encode(text, "utf-8"); 
        String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
        URL getUrl = new URL(getURL); 
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
        connection.connect(); 

        // 取得输入流，并使用Reader读取 
        BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
        StringBuffer sb = new StringBuffer(); 
        String line = ""; 
        while ((line = reader.readLine()) != null) { 
            sb.append(line); 
        } 
        reader.close(); 
        // 断开连接 
        connection.disconnect(); 
        String textRe = sb.substring(sb.indexOf("text")+7, sb.length()-2);
        return textRe;
        
    }
    

}