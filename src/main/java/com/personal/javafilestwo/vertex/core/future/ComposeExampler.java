package com.personal.javafilestwo.vertex.core.future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComposeExampler extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(ComposeExampler.class);

    public static void main(String[] args) {
        var vertex = Vertx.vertx();
        vertex.deployVerticle("com.personal.javafilestwo.vertex.future.ComposeExampler");

    }

    @Override
    public void start() throws Exception {
        Future<String> future = anAsyncAction();
        future.compose(this::anotherAsyncAction)
                .onComplete(ar -> {
                    if (ar.failed()) {
                        LOG.error("Error", ar.cause());
                    } else {
                        LOG.debug("Result: " + ar.result());
                    }
                });
    }

    private Future<String> anAsyncAction() {
        Promise<String> promise = Promise.promise();
        // mimic something that take times/ mimic something that take times
        vertx.setTimer(100, l -> promise.complete("world"));
        return promise.future();
    }

    private Future<String> anotherAsyncAction(String name) {
        Promise<String> promise = Promise.promise();
        // mimic something that take times
        vertx.setTimer(100, l -> promise.complete("hello " + name));
        return promise.future();
    }
}
