package com.hoggen.sublimation.Controller;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTest {

    public  static void main(String[] args){
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置niosocket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
        //设置管道的工厂

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
               ChannelPipeline pipeline =  Channels.pipeline();
               //数据解析
               pipeline.addLast("decoder",new StringDecoder());
               pipeline.addLast("hoggenT",new HoggenTHandler());
                return pipeline;
            }
        });


        bootstrap.bind(new InetSocketAddress(8099));

        System.out.println("start");

    }
}






























