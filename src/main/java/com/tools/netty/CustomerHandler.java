package com.tools.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CustomerHandler extends SimpleChannelInboundHandler {
    protected CustomerHandler() {
        super();
    }

    protected CustomerHandler(boolean autoRelease) {
        super(autoRelease);
    }

    protected CustomerHandler(Class inboundMessageType) {
        super(inboundMessageType);
    }

    protected CustomerHandler(Class inboundMessageType, boolean autoRelease) {
        super(inboundMessageType, autoRelease);
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }


}