package com.personal.javafilestwo.vertex.verticles;

import com.personal.javafilestwo.vertex.eventloops.WorkerThreadExample;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle{
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(MainVerticle.class);
    public static void main(String[] args) {
        final Vertx vertx =Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        LOG.debug("Start {}",getClass().getName());
        for(int i =0 ; i<10 ;i++){
            vertx.deployVerticle("com.personal.javafilestwo.vertex.verticles.SubVerticle1",new DeploymentOptions().setConfig(new JsonObject().put("i",i)));
        }
        vertx.deployVerticle("com.personal.javafilestwo.vertex.verticles.SubVerticle1",new DeploymentOptions().setInstances(20));
        vertx.deployVerticle("com.personal.javafilestwo.vertex.verticles.SubVerticle2");
        startPromise.complete();
    }
}