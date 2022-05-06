package com.personal.javafilestwo.vertex.core.verticles;

import io.vertx.core.AbstractVerticle;

public class SubVerticle1 extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        int i = config().getInteger("i");
        System.out.println("Start :"+getClass().getName()+ " Thread :"+Thread.currentThread()+ i);
    }
}
