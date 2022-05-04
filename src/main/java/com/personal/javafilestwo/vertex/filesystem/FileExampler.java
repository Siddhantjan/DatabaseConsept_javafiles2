package com.personal.javafilestwo.vertex.filesystem;

import io.vertx.core.Vertx;

public class FileExampler {
    public static void main(String[] args) {
        var vertex = Vertx.vertx();
    vertex.deployVerticle("com.personal.javafilestwo.vertex.filesystem.FileVerticle");
            vertex.close();
    }
}
