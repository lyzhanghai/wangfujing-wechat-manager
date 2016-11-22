package com.wfj.message.req;

/**
 * 语音消息
 * @Class Name VoiceMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class VoiceMessage extends BaseMessage {  
    // 媒体 ID   
    private String MediaId;  
    // 语音格式   
    private String Format;  

    public String getMediaId() {  
        return MediaId;  
    }  

    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  

    public String getFormat() {  
        return Format;  
    }  

    public void setFormat(String format) {  
        Format = format;  
    }  
}
