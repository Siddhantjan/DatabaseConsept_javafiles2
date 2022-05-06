package com.personal.javafilestwo.vertex.core.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
    private  static final Logger LOG= LoggerFactory.getLogger(MainVerticle.class);
    public static void main(String[] args) {
        var vertx = Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.worker.MainVerticle");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOG.debug("Started in Thread : {}",Thread.currentThread());
        vertx.deployVerticle("com.personal.javafilestwo.vertex.worker.WorkerVerticle",new DeploymentOptions()
                .setWorker(true));
        vertx.eventBus().request(
                "sample.data",
                "hello vert.x",
                r -> {
                    LOG.debug("[Main] Receiving reply ' " + r.result().body()
                            + "' in " + Thread.currentThread().getName());
                }
        );

        startPromise.complete();

    }
}
