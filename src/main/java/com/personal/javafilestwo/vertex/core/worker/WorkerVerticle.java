package com.personal.javafilestwo.vertex.core.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVerticle extends AbstractVerticle {
    private  static final Logger LOG= LoggerFactory.getLogger(WorkerVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
       LOG.debug("[Worker] Starting in : {}" + Thread.currentThread().getName());

        vertx.eventBus().<String>consumer("sample.data", message -> {
            LOG.debug("[Worker] Consuming data in : {} " + Thread.currentThread().getName());
            String body = message.body();
            message.reply(body.toUpperCase());
        });
        startPromise.complete();
    }
}
