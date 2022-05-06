package com.personal.javafilestwo.vertex.core.future;

import io.vertx.core.*;

public class FutureVert extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(FutureVert.class.getName());
    }

    @Override
    public void start() throws Exception {
       Future<String> future = method1();
        Future<String> future1 = method2();
        Future<String> future2 = method3();
        CompositeFuture.any(future,future1,future2).onComplete(h->{
            if (h.succeeded()){
                System.out.println(" any success");
            }
            else {
                h.cause().printStackTrace();
            }
        });
        CompositeFuture.all(future,future1,future2).onComplete(h->{
            if (h.succeeded()){
                System.out.println("all success");
            }
            else {
                h.cause().printStackTrace();
            }
        });
        CompositeFuture.join(future,future1,future2).onComplete(h->{
            if (h.succeeded()){
                System.out.println(" join success");
            }
            else {
                System.out.println(h.cause().getMessage());
            }
        });
    }

    private Future<String> method2() {
        Promise<String> promise = Promise.promise();
        // mimic something that take times/ mimic something that take times
        vertx.setTimer(100, l -> promise.fail("great"));
        return promise.future();
    }
    private Future<String> method3() {
        Promise<String> promise = Promise.promise();
        // mimic something that take times/ mimic something that take times
        vertx.setTimer(100, l -> promise.complete("WOW"));
        return promise.future();
    }

    private Future<String> method1() {
        Promise<String> promise = Promise.promise();
        // mimic something that take times/ mimic something that take times
        vertx.setTimer(100, l -> promise.complete("world"));
        return promise.future();
    }
}
