package com.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 服务端消息处理
 * @author -琴兽-
 *
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

//	@Override
//	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
//
//
//
//
//		ctx.channel().writeAndFlush("hi");
//		ctx.writeAndFlush("hi");
//	}

	/**
	 * 新客户端接入
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
		ctx.writeAndFlush("pong");
	}

	/**
	 * 客户端断开
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive");
	}

	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}


	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(1);
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req,"UTF-8");
//		System.out.println("receive response:" + body);
//		System.out.println(msg);

		ctx.channel().writeAndFlush(msg);
	}
}
