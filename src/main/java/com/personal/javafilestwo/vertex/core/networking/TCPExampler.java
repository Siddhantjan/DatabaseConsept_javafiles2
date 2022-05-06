package com.personal.javafilestwo.vertex.core.networking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;

public class TCPExampler extends AbstractVerticle {

    public static void main(String[] args) {
        var vertx=  Vertx.vertx();
      vertx.deployVerticle("com.personal.javafilestwo.vertex.networking.TCPExampler");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                // Just echo back the data
                socket.write(buffer);
            });
        });
        server.listen(1234, "localhost");
        server.close(res -> {
            if (res.succeeded()) {
                System.out.println("Server is now closed");
            } else {
                System.out.println("close failed");
            }
        });
        startPromise.complete();
    }
}
