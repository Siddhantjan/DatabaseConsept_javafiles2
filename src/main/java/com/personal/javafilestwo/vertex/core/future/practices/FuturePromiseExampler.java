package com.personal.javafilestwo.vertex.core.future.practices;

import io.vertx.core.*;

import java.util.Scanner;

public class FuturePromiseExampler extends AbstractVerticle {
    static int num1;
    public static void main(String[] args) {
        var vertx= Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.core.future.practices.FuturePromiseExampler");
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

                var res = addNumber(num1);

                res.onComplete(future -> {
                    if (res.succeeded()) {
                        var value = res.result();
                        System.out.println(value);

                    } else {
                        System.out.println(res.cause().getMessage());
                    }
                });
            }        catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });



        startPromise.complete();


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
