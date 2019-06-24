package com.hoggen.sublimation.JustForTest;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

public class HoggenTHandler extends SimpleChannelHandler {

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }


    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
       // ChannelBuffer messager = (ChannelBuffer) e.getMessage();
        String messge = (String) e.getMessage();
        System.out.println(messge);
       // System.out.println(e.getMessage());

//        ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer(("host: " + messge).getBytes());

        ctx.getChannel().write("host:  " + messge);

        super.messageReceived(ctx, e);
    }
}





































