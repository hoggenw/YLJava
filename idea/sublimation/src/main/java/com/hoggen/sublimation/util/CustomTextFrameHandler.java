package com.hoggen.sublimation.util;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class CustomTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        final String content = textWebSocketFrame.text();
        System.out.println("接收到数据："+content);

        // 回复数据
        TextWebSocketFrame respFrame = new TextWebSocketFrame("我收到了你的数据");
        if (channelHandlerContext.channel().isWritable()) {
            ChannelFuture future = channelHandlerContext.writeAndFlush(respFrame);
        }
    }
}
