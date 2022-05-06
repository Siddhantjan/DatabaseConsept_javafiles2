package com.personal.javafilestwo.vertex.web.blockinghandlers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingHandlersExampler extends AbstractVerticle {
    private static final Logger LOG= LoggerFactory.getLogger(BlockingHandlersExampler.class);
    public static void main(String[] args) {
        var vertx= Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.web.blockinghandlers.BlockingHandlersExampler");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        LOG.debug("Therad Type :"+Thread.currentThread());

        // Enable multipart form data parsing
        router.route().handler(BodyHandler.create());

        router.route().path("/discovery").blockingHandler(ctx-> {
            // Blocking handlers are allowed to block the calling thread
            // So let's simulate a blocking action or long running operation
            LOG.debug("Therad Type :"+Thread.currentThread());
            try {
                Thread.sleep(5000);
            } catch (Exception ignore) {
            }

            // Now call the next handler
            ctx.next();
        }, false);

        router.route().path("/discovery").handler(routingContext -> {
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
        startPromise.complete();
    }
}
