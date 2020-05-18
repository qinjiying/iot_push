package com.lxr.iot.mqtt;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * mqtt协议处理器
 *
 * @author lxr
 * @create 2017-11-20 13:38
 **/
@Slf4j
public  abstract  class MqttHander extends SimpleChannelInboundHandler<MqttMessage> {

    MqttHandlerIntf mqttHandlerApi;

    public MqttHander(MqttHandlerIntf mqttHandlerIntf){
        this.mqttHandlerApi=mqttHandlerIntf;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) throws Exception {
        MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
        Optional.ofNullable(mqttFixedHeader)
                .ifPresent(mqttFixedHeader1 -> doMessage(channelHandlerContext,mqttMessage));
    }

    public  abstract void doMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage);




    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("【DefaultMqttHandler：channelInactive】"+ctx.channel().localAddress().toString()+"关闭成功");
        mqttHandlerApi.close(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            mqttHandlerApi.doTimeOut(ctx.channel(),(IdleStateEvent)evt);
        }
        super.userEventTriggered(ctx, evt);
    }


}
