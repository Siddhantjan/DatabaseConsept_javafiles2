package com.personal.javafilestwo.vertex.core.eventloops;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkerThreadExample extends AbstractVerticle {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(WorkerThreadExample.class);

    public static void main(String[] args) {
        var ve = Vertx.vertx();
        ve.deployVerticle("com.personal.javafilestwo.vertex.eventloops.WorkerThreadExample");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.deployVerticle("com.personal.javafilestwo.vertex.eventloops.WorkerVerticle",
                new DeploymentOptions()
                        .setWorker(true)
                        .setWorkerPoolSize(1)
                        .setWorkerPoolName("my worker verticle"));
        startPromise.complete();
        executeBlockingCode();
    }

    private void executeBlockingCode() {
        vertx.executeBlocking(event -> {
            try {
                Thread.sleep(5000);
                event.complete();
            } catch (InterruptedException e) {
                LOG.debug("failed ",e);
                event.fail(e);
            }
        }, result -> {

            if (result.succeeded()) {
                LOG.debug("succeed :","Executing Blocking call done");
            } else {
                LOG.debug("failed : ", result.cause());
            }
        });

    }
}
