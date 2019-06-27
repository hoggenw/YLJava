package com.hoggen.sublimation.util.NettyHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.hoggen.sublimation.util.NettyHandler.GlobalUserUtil.channels;

public class CustomTextFrameHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomTextFrameHandler.class);
    private WebSocketServerHandshaker handshaker;
    private final String wsUri = "/hoggen";
    /**
     * 连接上服务器
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("【handlerAdded】====>"+ctx.channel().id());
        channels.add(ctx.channel());
        super.handlerAdded(ctx);
        logger.info("剩余客户端：{}", channels.size());
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("【handlerRemoved】====>"+ctx.channel().id());
        channels.remove(ctx);
        logger.info("剩余客户端：{}", channels.size());
    }

    /**
     * 连接异常   需要关闭相关资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("【系统异常】======>"+cause.toString());
        ctx.close();
        ctx.channel().close();
    }

    /**
     * 活跃的通道  也可以当作用户连接上客户端进行使用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("【channelActive】=====>"+ctx.channel());
        // TODO Auto-generated method stub

        System.out.println(ctx.channel().remoteAddress()+"   ----Acrive");
//        try {
//            //ctx.writeAndFlush("Welcome you to here"+ InetAddress.getLocalHost().getHostName());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
       // }
        super.channelActive(ctx);
    }

    /**
     * 不活跃的通道  就说明用户失去连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("【channelInactive】=====>"+ctx.channel());
    }

    /**
     * 这里只要完成 flush
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 这里是保持服务器与客户端长连接  进行心跳检测 避免连接断开
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()){
                //读空闲（服务器端）
                case READER_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】读空闲（服务器端）");
                    //ctx.writeAndFlush(ping);
                    break;
                //写空闲（客户端）
                case WRITER_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】写空闲（客户端）");
                   // ctx.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】读写空闲");
                    ChannelFuture writeAndFlush = ctx.channel().writeAndFlush(new TextWebSocketFrame(
                             " you will be close：" + new java.util.Date().toString()));
                    writeAndFlush.addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            ctx.channel().close();
                        }
                    });

                    break;
            }
        }
        super.userEventTriggered(ctx,evt);
    }


    // 处理HTTP的代码
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws UnsupportedEncodingException {
        System.out.println("httprequest get");
        System.out.println("handleHttpRequest method==========" + req.method());
        System.out.println("handleHttpRequest uri==========" + req.uri());
        // 如果HTTP解码失败，返回HHTP异常
        Map<String, String> parmMap = new HashMap<>();
        if (req instanceof HttpRequest) {
            HttpMethod method = req.method();
            System.out.println("this is httpconnect");
            // 如果是websocket请求就握手升级
            if (wsUri.equalsIgnoreCase(req.uri())) {
                System.out.println("websocket 请求接入");
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                        "http://localhost:8880/index.html#/", null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {
                    handshaker.handshake(ctx.channel(), req);
                }
            }
            if (HttpMethod.POST == method) {
                // 是POST请求
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(req);
                decoder.offer(req);
                System.out.println(decoder.getBodyHttpDatas());
            }
            if (HttpMethod.GET == method) {
                // 是GET请求
                System.out.println(req.content());
                // 编码解码
                ByteBuf in = (ByteBuf) req.content();
                byte[] byt = new byte[in.readableBytes()];
                in.readBytes(byt);
                String body = new String(byt, "UTF-8");
                System.out.println("server channelRead...; received收到客户端消息:" + body);
                QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
                //System.out.println(decoder.toString());
                /*
                 * ctx.channel().writeAndFlush(new
                 * TextWebSocketFrame("服务端数据"+body));
                 */
                // 将数据写入通道
                channels.writeAndFlush(new TextWebSocketFrame(body));
            }
        }
    }

    // 握手请求不成功时返回的应答
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
    }
    // 处理Websocket的代码
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        System.out.println("websocket get");
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            logger.info("【ping】");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
//        if(frame instanceof PongWebSocketFrame){
//            logger.info("【pong】");
//            PingWebSocketFrame ping = new PingWebSocketFrame(frame.content().retain());
//            ctx.channel().writeAndFlush(ping);
//            return ;
//        }
        // 文本消息，不支持二进制消息
        if (frame instanceof TextWebSocketFrame) {
            // 返回应答消息
            String request = ((TextWebSocketFrame) frame).text();
            ctx.channel().writeAndFlush(new TextWebSocketFrame(
                    request + " , 欢迎使用Netty WebSocket服务，现在时刻：" + new java.util.Date().toString()));
        }else {
            ctx.channel().writeAndFlush(frame);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {// 如果是HTTP请求，进行HTTP操作
            System.out.println("into hettpHandle");
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 如果是Websocket请求，则进行websocket操作
            System.out.println("into websockethandel");
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

}
