package com.personal.javafilestwo.vertex.asynctask;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiffVerticle extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(DiffVerticle.class);
    @Override
    public void start(Promise<Void> startPromise) throws Exception {

       LOG.debug("In OtherVerticle.start (async)");


        vertx.setTimer(2000, tid -> {
            LOG.debug("Startup tasks are now complete, OtherVerticle is now started!");
            startPromise.complete();

        });

    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {

        // If we have slow cleanup tasks to perform, you can similarly override the async stop method

        vertx.setTimer(2000, tid -> {

          LOG.debug("Cleanup tasks are now complete, OtherVerticle is now stopped!");

            stopPromise.complete();

        });

    }
}
