//使用脚本检测浏览器的标签支持情况  
var support = !!document.createElement("audio").canPlayType;  
if (!support) {  
    alert("你的浏览器不支持本视频播放");  
}  
// 定义全局的视频对象  
var e1 = null;  
window.addEventListener("load", function() {  
    e1 = document.getElementById("myPlayer");  
});  
/*前进：一分钟 */  
function Next() {  
    e1.currentTime+=10; //设置属性currentTime,快进10s  
}  
/*后退：一分钟 */  
function Prev() {  
    e1.currentTime-=10; //设置属性currentTime,后退10s  
}  
/*播放/暂停*/  
function Play(e) {  
    if(e1.paused){  
        e1.play();  
        document.getElementById("btn1").innerHTML="暂停"  
    }else{  
        e1.pause();  
        document.getElementById("btn1").innerHTML="播放"  
    }  
}  
/*慢进：小于等于1时，每次都只减慢0.2的速率；大于1时，每次减1 */  
function Slow(){  
    if(e1.playbackRate<=1){  
        e1.playbackRate-=0.2;  
    }else{  
        e1.playbackRate-=1;  
    }  
    document.getElementById("rate").innerHTML=fps2fps(e1.playbackRate);  
}  
/*慢进：小于等于1时，每次都只减慢0.2的速率；大于1时，每次减1 */  
function Fast(){  
    if(e1.playbackRate<1){  
        e1.playbackRate+=0.2;  
    }else{  
        e1.playbackRate+=1;  
    }  
    document.getElementById("rate").innerHTML=fps2fps(e1.playbackRate);  
}  
function fps2fps(fps){  
    if(fps<1){  
        return fps.toFixed(1);  
    }else{  
        return fps;  
    }  
}  
/*静音*/  
function Muted(e){  
    if(e1.muted){  
        e1.muted=false;  
        e.innerHRML="X";  
        document.getElementById("volume").value=e1.volume;  
    }else{  
        e1.muted=true;  
        e.innerHRML="x";  
        document.getElementById("volume").value=0;  
    }  
}  
/*调整音量*/  
function Volume(e){  
    if(e1.muted==true){  
        e1.muted=false;  
    }  
    e1.volume=e.value;  
}  
/* 进度信息：控制进度条，并显示进度时间*/  
function Progress(){  
    var p1=document.getElementById("progress");  
    p1.style.width=(e1.currentTime/e1.duration)*720+"px";  
    document.getElementById("info").innerHTML=s2time(e1.currentTime)+"/"+s2time(e1.duration);  
}  
function s2time(s){  
    var m=parseFloat(s/60).toFixed(0);  
    s=parseFloat(s%60).toFixed(0);  
    return (m<10? "0"+m:m)+":"+(s<10?"0"+s:s);  
}  
/* 网页加载完毕后，把进度处理函数添加至视频对象的timeupdate事件中*/  
window.addEventListener("load",function(){  
    e1.addEventListener("timeupdate",Progress);  
});  
/*给window.onload事件添加进度处理函数*/  
window.addEventListener("load",Progress);  