package com.personal.javafilestwo.vertex.eventloops;

import io.vertx.core.*;

import java.util.concurrent.TimeUnit;

public class EventLoopEmample extends AbstractVerticle {
    public static void main(String[] args) {
    var v = Vertx.vertx(
            new VertxOptions()
                    .setMaxEventLoopExecuteTime(500)
                    .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
                    .setBlockedThreadCheckInterval(1)
                    .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
                //    .setEventLoopPoolSize(2)
    );
    v.deployVerticle("com.personal.javafilestwo.vertex.eventloops.EventLoopEmample",new DeploymentOptions().setInstances(4));
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
       startPromise.complete();
        Thread.sleep(5000);
    }
}
