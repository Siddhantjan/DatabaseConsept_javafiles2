package com.personal.javafilestwo.vertex.core.future.practices;

import io.vertx.core.*;

import java.util.Scanner;

public class MultiFutureExampler extends AbstractVerticle {
    static int num1;

    public static void main(String[] args) {
        var vertx= Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.core.future.practices.MultiFutureExampler");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.executeBlocking(event -> {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("enter a number");
                num1 = sc.nextInt();
                event.complete();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }).onComplete(val->{
            try {
        var future1=addNumber(num1);
        var future2 = multiNumber(num1);
        var future3 = divideNumber(num1);
        CompositeFuture.join(future1,future2 , future3).onComplete(handler ->{
            if(handler.succeeded()){
                var result= future1.result()+future2.result()+future3.result();
                System.out.println(result);
            }
            else{
                System.out.println(handler.cause().getMessage());
            }
        });
    }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        startPromise.complete();

    }
    private Future<Integer> divideNumber(int num1) {
        Promise<Integer> promise = Promise.promise();
        try{
            promise.complete(num1 / 4);
        }
        catch (Exception e){
            promise.fail(e.getMessage());
        }
        return promise.future();
    }

    private Future<Integer> multiNumber(int num1) {
        Promise<Integer> promise = Promise.promise();
        try{
            promise.complete(num1 * 10);
        }
        catch (Exception e){
            promise.fail(e.getMessage());
        }
        return promise.future();
    }

    private Future<Integer> addNumber(int num1) {
        Promise<Integer> promise = Promise.promise();
        try{
            promise.complete(num1 + 25);
        }
        catch (Exception e){
            promise.fail(e.getMessage());
        }
        return promise.future();
    }


}
