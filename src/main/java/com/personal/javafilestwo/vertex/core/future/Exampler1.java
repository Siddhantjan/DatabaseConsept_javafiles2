package com.personal.javafilestwo.vertex.core.future;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exampler1 extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(Exampler1.class);

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle("com.personal.javafilestwo.vertex.future.Exampler1");
    }
//
//    @Override
//    public void start() throws Exception {
//        FileSystem fs = vertx.fileSystem();
//
//        Future<FileProps> future = fs.props("/home/siddhant/IdeaProjects/DatabaseConsept/src/main/java/com/personal/javafilestwo/vertex/future/file.txt");
//
//        future.onComplete((AsyncResult<FileProps> ar) -> {
//            if (ar.succeeded()) {
//                FileProps props = ar.result();
//                LOG.debug("File size = " + props.size());
//            } else {
//                LOG.error("Failure: " + ar.cause().getMessage());
//            }
//        });
//    }

    @Override
    public void start() throws Exception {
//        FileSystem fs = vertx.fileSystem();
//
//        Future<Void> future = fs
//                .createFile("/home/siddhant/IdeaProjects/DatabaseConsept/src/main/java/com/personal/javafilestwo/vertex/future/")
//                .compose(v -> {
//                    // When the file is created (fut1), execute this:
//                    return fs.writeFile("/foo", Buffer.buffer());
//                })
//                .compose(v -> {
//                    // When the file is written (fut2), execute this:
//                    return fs.move("/foo", "/bar");
//                });
    }
}
