package com.wkb.chat.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/


public class FileUtil {
	
	/*private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);*/
    /**
     * 获取保存文件的位置,jar所在目录的路径
     *
     * @return
     */
    public static String getUplodFilePath() {
        String path = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(1, path.length());
        try {
            path = java.net.URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(0, lastIndex);
        File file = new File("");
        return file.getAbsolutePath() + "/";
    }
    

}
