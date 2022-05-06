package com.personal.javafilestwo.vertex.core.datagram;

import io.vertx.core.Vertx;

public class DataGramExampler {
    public static void main(String[] args) {
        var vertex = Vertx.vertx();
        vertex.deployVerticle("com.personal.javafilestwo.datagram.ClientVerticle").compose(
        file ->vertex.deployVerticle("com.personal.javafilestwo.datagram.ServerVerticle"));
    }
}
