package com.wfj.message.resp;

/**
 * 音乐消息
 * @Class Name MusicMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class MusicMessage extends BaseMessage {  
    // 音乐   
    private Music Music;  

    public Music getMusic() {  
        return Music;  
    }  

    public void setMusic(Music music) {  
        Music = music;  
    }  
}
