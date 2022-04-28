package com.personal.javafilestwo.vertex.verticles;

import io.vertx.core.AbstractVerticle;

public class SubVerticle2 extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.println("Start :"+getClass().getName());
    }
}