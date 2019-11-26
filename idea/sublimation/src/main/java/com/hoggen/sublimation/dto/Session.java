package com.hoggen.sublimation.dto;

import java.io.Serializable;
import java.net.SocketAddress;

import io.netty.channel.Channel;
import lombok.Data;

@Data
public class Session implements Serializable {
    //Session的唯一标识
    private String id;
    //和Session相关的channel,通过它向客户端回送数据
    private Channel channel = null;
    //上次通信时间
    private long lastCommunicateTimeStamp = 0l;

    //快速构建一个新的Session
    public static Session buildSession(Channel channel,String id) {
        Session session = new Session();
        session.setChannel(channel);
        //此处暂且使用netty生成的类似UUID的字符串,来标识一个session
        session.setId(id);
        session.setLastCommunicateTimeStamp(System.currentTimeMillis());
        return session;
    }
}
