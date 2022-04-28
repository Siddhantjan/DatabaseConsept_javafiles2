package com.personal.javafilestwo.vertex.eventloops;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class WorkerThreadExample extends AbstractVerticle {
    public static void main(String[] args) {
        var ve= Vertx.vertx();
        ve.deployVerticle("com.personal.javafilestwo.vertex.eventloops.WorkerThreadExample");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        startPromise.complete();

    }
}
