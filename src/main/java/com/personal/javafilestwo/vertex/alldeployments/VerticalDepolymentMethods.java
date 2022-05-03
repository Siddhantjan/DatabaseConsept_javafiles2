package com.personal.javafilestwo.vertex.alldeployments;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticalDepolymentMethods extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(VerticalExp.class);

    public static void main(String[] args) {
        var vertx = Vertx.vertx();
        //simple way
        // vertx.deployVerticle(new VerticalExp());
//        JSONObject obj=new JSONObject();
//        obj.put("1","hey");
//      //  vertx.deployVerticle(new VerticalExp(),new DeploymentOptions().setConfig(JsonObject.mapFrom(obj)));
//        vertx.deployVerticle(new VerticalExp(), handler ->{
//           if( handler.succeeded()){
//                LOG.debug("Sucess");
//            }
//           else {
//               LOG.debug("Fail :{}",handler.cause().getMessage());
//           }
//        });
        vertx.deployVerticle(new VerticalDepolymentMethods(), new DeploymentOptions());

    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOG.debug("Main vertical started : {}", getClass().getName());
        // Deploy a verticle and don't wait for it to start
        //    vertx.deployVerticle(new VerticalExp());
        // Deploy a verticle and wait for it start
//        vertx.deployVerticle(new VerticalExp(), res -> {
//            if (res.succeeded()) {
//                String deploymentID = res.result();
//                LOG.debug("Other verticle deployed ok, deploymentID = " + deploymentID);
//                // You can also explicitly undeploy a verticle deployment.
//                // Note that this is usually unnecessary as any verticles deployed by a verticle will be automatically
//                // undeployed when the parent verticle is undeployed
//
//                vertx.undeploy(deploymentID, res2 -> {
//                    if (res2.succeeded()) {
//                       LOG.debug("Undeployed ok!");
//                    } else {
//                        LOG.debug("ERROR : {}",res2.cause().getMessage());
//                    }
//                });
//
//            } else {
//                LOG.debug("ERROR : {}",res.cause().getMessage());
//            }
//        });

        // Deploy specifying some config
//        JsonObject config = new JsonObject().put("foo", "bar");
//        vertx.deployVerticle(new VerticalExp(), new DeploymentOptions().setConfig(config));

        // Deploy 10 instances
        //  vertx.deployVerticle(new VerticalExp(), new DeploymentOptions().setInstances(10));

        // Deploy it as a worker verticle
        //    vertx.deployVerticle(new VerticalExp(), new DeploymentOptions().setWorker(true));


    }

    static class VerticalExp extends AbstractVerticle {
        private static final Logger LOG = LoggerFactory.getLogger(VerticalExp.class);

        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            var obj = config();
            LOG.debug("Start : {}", "Verticle is started");
            LOG.debug("config : {}", config());
            startPromise.complete();
        }

        //    @Override
//        public void stop(Promise<Void> stopPromise) throws Exception {
//            LOG.debug("Stop : {}","Verticle stopped");
//            stop();
//        }
    }
}
