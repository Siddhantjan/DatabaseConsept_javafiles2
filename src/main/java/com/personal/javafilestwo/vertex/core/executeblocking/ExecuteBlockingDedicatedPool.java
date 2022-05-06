package com.personal.javafilestwo.vertex.core.executeblocking;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteBlockingDedicatedPool  {
    private static final Logger LOG = LoggerFactory.getLogger(ExecuteBlockingDedicatedPool.class);
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle("com.personal.javafilestwo.vertex.executeblocking.ExecuteBlockingExampler",
                new DeploymentOptions()
                        .setWorkerPoolName("Example execute blocking")
                        .setMaxWorkerExecuteTime(1200)
                        .setWorkerPoolSize(5)
        ,er->{
            if (er.failed()){
                LOG.error("Error",er.cause());
            }
                });

    }
}
