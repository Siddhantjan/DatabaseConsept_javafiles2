package com.personal.javafilestwo.vertex.core.networking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class HTTPExampler extends AbstractVerticle {
    public static void main(String[] args) {
        var vertx= Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.networking.HTTPExampler");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.listen(8080, "myhost.com", res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening!");
            } else {
                System.out.println("Failed to bind!");
            }
        });
        startPromise.complete();
    }
}
