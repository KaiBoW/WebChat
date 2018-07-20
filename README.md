# WebChat

#### 项目介绍
基于spring boot2.0+百度语音转换+图灵机器人

#### 主要功能
1.实现web端在线智能聊天
2.基于百度语音识别实现文字转语音
3.实现web端音频流与视频流的录制，实现上传，播放


#### 准备工作

1.申请百度语音api  地址：http://ai.baidu.com/

2.申请图灵api  地址：http://www.tuling123.com/

#### 属性配置
配置util包中BaiDuAi.java，TuLing.java对应的key
```
BaiDuAi.java：
    public static final String APP_ID = "你的APP_ID";
    public static final String API_KEY = "你的API_KEY";
    public static final String SECRET_KEY = "你的SECRET_KEY";
TuLing.java：
    public static String  APIKEY = "你的APIKEY";

```

##### 项目代码未整理，有很多重复代码，可以按照需求提取自己需要的代码

##### 邮箱：961050554@qq.com 欢迎一起交流进步\(0^◇^0)/