package com.personal.javafilestwo.vertex;

import io.vertx.core.*;

public class VertexBase extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception{
        vertx.createHttpServer().requestHandler(req->{
           req.response()
                   .putHeader("context-type","text/plain")
                   .end("hey buddy from vert.x Core");
        }).listen(8080,http->{
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("Http server start on port 8080");
            }else {
                startPromise.fail(http.cause());
            }
        });
    }
    public static void main(String[] args) {
        var vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(16));
            vertx.deployVerticle(new VertexBase());

    }
}
