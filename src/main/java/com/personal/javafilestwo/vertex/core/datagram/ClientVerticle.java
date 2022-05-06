package com.personal.javafilestwo.vertex.core.datagram;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientVerticle extends AbstractVerticle {
    private static final Logger LOG= LoggerFactory.getLogger(ClientVerticle.class);
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
        socket.listen(1234, "127.0.0.1", asyncResult -> {
            if (asyncResult.succeeded()) {
                socket.handler(packet -> {
                    // Do something with the packet
                    LOG.debug("Received "+packet.data());
                });
                socket.unlistenMulticastGroup("127.0.0.1", asyncResult3 -> {
                    LOG.debug("Unlisten succeeded? " + asyncResult3.succeeded());
                });
            } else {
               LOG.error("Listen failed" + asyncResult.cause());
            }
        });
        startPromise.complete();
    }
}
