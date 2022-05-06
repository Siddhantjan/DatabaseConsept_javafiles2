package com.personal.javafilestwo.vertex.core.datagram;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerVerticle extends AbstractVerticle {
    private static final Logger LOG= LoggerFactory.getLogger(ServerVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
        Buffer buffer = Buffer.buffer("content");
// Send a Buffer
        socket.send(buffer, 1234, "127.0.0.1", asyncResult -> {
            LOG.debug("Send succeeded? " + asyncResult.succeeded());
        });
// Send a String
        socket.send("A string used as content", 1234, "127.0.0.1", asyncResult -> {
            LOG.debug("Send succeeded? " + asyncResult.succeeded());

        });
        startPromise.complete();
    }
}
