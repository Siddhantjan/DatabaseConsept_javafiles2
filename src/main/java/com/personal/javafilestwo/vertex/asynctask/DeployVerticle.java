package com.personal.javafilestwo.vertex.asynctask;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeployVerticle extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(DeployVerticle.class);
    public static void main(String[] args) {
        var vertex = Vertx.vertx();
        vertex.deployVerticle("com.personal.javafilestwo.vertex.asynctask.DeployVerticle");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOG.debug("Start : {}","Main Verticle is started and call your buddy verticle");
        vertx.deployVerticle("com.personal.javafilestwo.vertex.asynctask.DiffVerticle",res ->{
            if (res.succeeded()){
                String deployID = res.result();
                LOG.debug("Other verticle deployed ok, deploymentID = " + deployID);

                vertx.undeploy(deployID, res2 -> {
                    if (res2.succeeded()) {
                        LOG.debug("Undeployed ok!");
                    } else {
                        res2.cause().printStackTrace();
                    }
                });
            } else {
                res.cause().printStackTrace();
            }
        });
    }
}
