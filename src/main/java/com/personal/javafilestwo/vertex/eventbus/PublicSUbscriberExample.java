package com.personal.javafilestwo.vertex.eventbus;

import com.personal.javafilestwo.vertex.verticles.SubVerticle1;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PublicSUbscriberExample extends AbstractVerticle {
    public static void main(String[] args) {
        var vertx= Vertx.vertx();
        vertx.deployVerticle(new Publish());
        vertx.deployVerticle(new Subscriber1());
       // vertx.deployVerticle(new Subscriber2());
        vertx.deployVerticle(Subscriber2.class.getName(),new DeploymentOptions().setInstances(2));
    }
    public static class Publish extends AbstractVerticle{
        @Override
        public void start(Promise<Void> startPromise) throws Exception {
          vertx.setPeriodic(Duration.ofSeconds(10).toMillis(),id->{

              vertx.eventBus().publish(Publish.class.getName(),"A message for everyone");
          });
            startPromise.complete();

        }
    }
    public static class Subscriber1 extends AbstractVerticle{
        private static final Logger LOG = LoggerFactory.getLogger(Subscriber1.class);

        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            vertx.eventBus().<String>consumer(Publish.class.getName(),message ->{
               LOG.debug("received : {}",message.body());
            });
        }
    }
    public static class Subscriber2 extends AbstractVerticle{
        private static final Logger LOG = LoggerFactory.getLogger(Subscriber2.class);

        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            vertx.eventBus().<String>consumer(Publish.class.getName(),message ->{
                LOG.debug("received : {}",message.body());
            });
        }
    }
}
