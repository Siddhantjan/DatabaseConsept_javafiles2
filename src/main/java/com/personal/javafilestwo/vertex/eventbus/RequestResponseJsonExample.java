package com.personal.javafilestwo.vertex.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseJsonExample {

    public static void main(String[] args) {
        var vertex = Vertx.vertx();
        vertex.deployVerticle(new RequestVerticle());
        vertex.deployVerticle(new ResponseVerticle());
    }

    static class RequestVerticle extends AbstractVerticle {
        private static final Logger LOG = LoggerFactory.getLogger(RequestVerticle.class);
        private static final String ADDRESS = "my.request.address";
        @Override
        public void start(Promise<Void> startPromise) throws Exception {

            var eventBus =vertx.eventBus();

            final var message = new JsonObject().put("message","hey World!")
                    .put("version",1)
                    .put("metric","linux");
            //  LOG.debug("sending message : {} ", message);
            eventBus.<JsonArray>request(ADDRESS, message, reply -> {
                LOG.debug("Response : {}", reply.result().body());
            });
            startPromise.complete();
        }
    }

    static class ResponseVerticle extends AbstractVerticle {
        private static final Logger LOG = LoggerFactory.getLogger(ResponseVerticle.class);

        @Override
        public void start(Promise<Void> startPromise) throws Exception {

            vertx.eventBus().<JsonObject>consumer(RequestVerticle.ADDRESS, message->{
                LOG.debug("Received Message: {}",message.body());
                message.reply(new JsonArray().add("one").add("two").add("three"));
            });

            startPromise.complete();}

    }
}
