package com.personal.javafilestwo.vertex.core.jsonstreaming;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.parsetools.JsonEventType;
import io.vertx.core.parsetools.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class JsonStreamingExampler extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(JsonStreamingExampler.class);

    public static void main(String[] args) {
        var vertx = Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.jsonstreaming.JsonStreamingExampler",
                ar -> {
                    if (ar.failed())
                        LOG.error("Error", ar.cause());
                });
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.fileSystem().open("large.json", new OpenOptions(), ar -> {
            if (ar.succeeded()) {
                AsyncFile asyncFile = ar.result();
                AtomicInteger c = new AtomicInteger();
                JsonParser jsonParser = JsonParser.newParser(asyncFile);

                jsonParser.objectValueMode()
                        .exceptionHandler(t -> {
                           LOG.debug("error : {}",t.getMessage());
                            asyncFile.close();
                        })

                        .handler(event -> {
                            // Got a new JsonEvent
                            // In object-value mode the event should always be of type "VALUE"
                            if (event.type() == JsonEventType.VALUE) {
                                // Use mapTo to map the JSON obect to a Java class
                                DataPoint dataPoint = event.mapTo(DataPoint.class);
                                // Let's not log all objects from this giant file...
                                if (c.incrementAndGet() % 100 == 0) {
                                    System.out.println("DataPoint = " + dataPoint);
                                }
                            }
                        })
                        .endHandler(v -> {
                            // LOG.debug("message :{}",asyncFile.read());
                            LOG.debug("Done!");
                            asyncFile.close();
                        });
            }
        });
    }
}


