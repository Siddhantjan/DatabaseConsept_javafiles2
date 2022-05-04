package com.personal.javafilestwo.vertex.executeblocking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteBlockingExampler extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(ExecuteBlockingExampler.class);
    public static void main(String[] args) {
        var vertex = Vertx.vertx();
        vertex.deployVerticle("com.personal.javafilestwo.vertex.executeblocking.ExecuteBlockingExampler",
                new DeploymentOptions().setWorker(true),
                er ->{
            if (er.failed()){
                LOG.error("Error",er.cause());
            }
        });
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.createHttpServer().requestHandler( request -> {

            vertx.<String>executeBlocking(promise ->{
                try{
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    LOG.error("Error : {}",e.getMessage());
                }
                String result = "Siddhant!";
                promise.complete(result);

            },res ->{
                if (res.succeeded()) {request.response().putHeader("content-type", "text/plain").end(res.result());

            } else {
              LOG.error("Error : {}",  res.cause().getMessage());
            }
            });

        }).listen(8080);
    }
}
