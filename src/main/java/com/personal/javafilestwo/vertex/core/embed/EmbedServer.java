package com.personal.javafilestwo.vertex.core.embed;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class EmbedServer extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("Hello World!")).listen(8080);
    }
}
