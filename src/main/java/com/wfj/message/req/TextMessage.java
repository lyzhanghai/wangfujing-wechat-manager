package com.wfj.message.req;

/**
 * 文本消息
 * @Class Name TextMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class TextMessage extends BaseMessage {  
    // 消息内容   
    private String Content;  

    public String getContent() {  
        return Content;  
    }  

    public void setContent(String content) {  
        Content = content;  
    }  
}