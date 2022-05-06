package com.personal.javafilestwo.vertex.core.filesystem;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

public class FileVerticle extends AbstractVerticle {
    private static final Logger LOG= LoggerFactory.getLogger(FileVerticle.class);
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        FileSystem fs= vertx.fileSystem();
        //TODO File created name.txt
//        fs.createFile("/home/siddhant/IdeaProjects/DatabaseConsept/name.txt",handler->{
//            if (handler.succeeded()){
//                LOG.debug("File created successfully");
//            }
//            else{
//                LOG.error("ERROR "+handler.cause().getMessage());
//            }
//        });
//        vertx.executeBlocking(event -> {
//            LOG.debug("Thread "+ Thread.currentThread());
//            if (event.tryComplete()) {
//                //TODO write a message to file
//                String message = "Hey I am great";
//               fs.writeFile("/home/siddhant/IdeaProjects/DatabaseConsept/name.txt", Buffer.buffer().appendString((message)));
//            }
//                    else {
//                        event.fail("Fail");
//                    }
//        }).onComplete(handler ->{
//            LOG.debug("Thread "+ Thread.currentThread());
//            if (handler.succeeded()){
//               LOG.debug("Message write successfully");
//           }
//           else{
//               LOG.error("ERROR :"+handler.cause().getMessage());
//           }
//        });

        vertx.executeBlocking(event -> {
            try{
                fs.open("/home/siddhant/IdeaProjects/DatabaseConsept/name.txt", new OpenOptions(),res->{
                   if (res.succeeded()) {
                       AsyncFile file = res.result();
                       Buffer buffer = Buffer.buffer().appendString("heyy");
                       file.write(buffer,ar->{
                          if (ar.succeeded()){
                              LOG.debug("Written");
                          }
                          else{
                              LOG.error("ERROR"+ar.cause().getMessage());
                          }
                       });
                   }else {
                       LOG.error("ERROR "+res.cause().getMessage());
                   }
                });
            }catch (Exception e){

                LOG.error("ERROR "+e.getMessage());
            }
        });

        startPromise.complete();
    }
}
