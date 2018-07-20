package com.wkb.chat.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

//返回JSON格式的字符串
public class ReturnJson {
	
	public static void toJson(HttpServletResponse response, Object data) {
		Gson gson=new Gson();
		String result = gson.toJson(data);
		response.setContentType("audio/wav"); // 设置响应编码格式
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
		out.flush();
		out.close();
		/*
		 * text/html;charset=utf-8		-->html 
		 * "text/plain; charset=utf-8	-->文本 
		 * text/javascript				-->json数据
		 * application/xml				-->xml数据
		 */
	}
	
	
	public static String getString(ServletInputStream inputStream) {
		String data = null;
		int len;
		byte buf[] = new byte[1024];
		try {
			while ((len = inputStream.read(buf)) != -1) {
				data = new String(buf, 0, len, "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
