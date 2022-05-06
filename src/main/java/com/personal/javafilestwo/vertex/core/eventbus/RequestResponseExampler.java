package com.personal.javafilestwo.vertex.core.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseExampler {

    public static void main(String[] args) {
        var vertex = Vertx.vertx();
        vertex.deployVerticle(new RequestVerticle());
        vertex.deployVerticle(new ResponseVerticle());
    }

    static class RequestVerticle extends AbstractVerticle {
        private static final Logger LOG = LoggerFactory.getLogger(RequestResponseExampler.class);
        private static final String ADDRESS = "my.request.address";

        @Override
        public void start(Promise<Void> startPromise) throws Exception {

            var eventBus = vertx.eventBus();

            final String message = "Hey buddy";
            //  LOG.debug("sending message : {} ", message);
            eventBus.<String>request(ADDRESS, message, reply -> {

                LOG.debug("Response : {}", reply.result().body());

            });
            startPromise.complete();
        }
    }

    static class ResponseVerticle extends AbstractVerticle {
        private static final Logger LOG = LoggerFactory.getLogger(ResponseVerticle.class);

        @Override
        public void start(Promise<Void> startPromise) throws Exception {

            vertx.eventBus().consumer(RequestVerticle.ADDRESS, message -> {
                LOG.debug("Received Message: {}", message.body());
                message.reply("Received your message. Thanks!");
            });

            startPromise.complete();
        }

    }
}