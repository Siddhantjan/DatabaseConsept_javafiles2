package com.personal.javafilestwo.vertex.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.TimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorld.class);
    public static void main(String[] args) {
    var vertx=Vertx.vertx();
    vertx.deployVerticle("com.personal.javafilestwo.vertex.web.HelloWorld");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
//        http server core concept
//        HttpServer server = vertx.createHttpServer();
//        server.requestHandler(request -> {
//
//            // This handler gets called for each request that arrives on the server
//            HttpServerResponse response = request.response();
//            response.putHeader("content-type", "text/plain");
//
//            // Write to the response and end it
//            response.end("Hello Siddhant! How are you?");
//        }).listen(8080);
//        startPromise.complete();


//        HttpServer server = vertx.createHttpServer();
//
//        Router router = Router.router(vertx);
//
//        router.route().handler(ctx -> {
//
//            // This handler will be called for every request
//            HttpServerResponse response = ctx.response();
//            response.putHeader("content-type", "text/plain");
//
//            // Write to the response and end it
//            response.end("Hello World from Vert.x-Web!");
//        });
//
//        server.requestHandler(router).listen(8080);


        final Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
//        router.route().handler(routingContext -> {
//            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
//        });
        // host  particular path
//        router.route().handler(ctx -> {
//            // This handler will be called for every request
//            HttpServerResponse response = ctx.response();
//            response.putHeader("content-type", "text/plain");
//           // Write to the response and end it
//           response.end("Hello World from Vert.x-Web!");
//        });
//        vertx.createHttpServer().requestHandler(router).listen(8080);

//        router.route().method(HttpMethod.POST).path("/discovery").handler(ctx -> {
//
//            HttpServerResponse response = ctx.response();
//            // enable chunked responses because we will be adding data as
//            // we execute over other handlers. This is only required once and
//            // only if several handlers do output.
//            response.setChunked(true);
//
//            response.write("route1\n");
//
//            // Call the next matching route after a 5 second delay
//           ctx.vertx().setTimer(5000, tid -> ctx.next());
//        });
//
//        router.route().handler(ctx -> {
//
//            HttpServerResponse response = ctx.response();
//            response.write("route2\n");
//
//            // Call the next matching route after a 5 second delay
//            ctx.vertx().setTimer(5000, tid -> ctx.next());
//        });
     /*   send json data
     router.route().method(HttpMethod.POST).path("/discovery")
                // this handler will ensure that the response is serialized to json
                // the content type is set to "application/json"
                .respond(
                        ctx -> Future.succeededFuture(new JsonObject().put("hello", "world")));
     */

//        router.route().handler(ctx -> {
//
//            HttpServerResponse response = ctx.response();
//            response.write("route3");
//
//            // Now end the response
//            ctx.response().end();
//        });
//
//      vertx.createHttpServer().requestHandler(router).listen(8080);

//        router.route().method(HttpMethod.POST).path("/discovery").handler(handler -> {
//                    HttpServerResponse response = handler.response();
//        try{
//            var data = handler.getBodyAsJson();
//            if (data.isEmpty()){
//                response.setStatusCode(200).putHeader("content-type", "application/json").end(
//                        new JsonObject().put("message","Unsuccessful").put("ERROR", "Json me kuch toh daal  bhai").encodePrettily()
//                );
//            }
//            else {
//                response.setStatusCode(200).putHeader("content-type", "application/json").end(
//                        new JsonObject().put("message","successful").put("ERROR", "Json barabar h bhai").encodePrettily()
//                );
//            }
//        }
//        catch (Exception e){
//            response.setStatusCode(200).putHeader("content-type", "application/json").end(
//                    new JsonObject().put("message", "Json Format galat h bhai").put("ERROR",e.getMessage()).encodePrettily()
//            );
//        }
//        });


//path with regex
//router.route().method(HttpMethod.GET).pathRegex(".*discovery").handler(ctx->{
//    ctx.response().putHeader("content-type", "application/json").end(new JsonObject().put("data",Thread.currentThread().getName()).encodePrettily());
//        });
        //http method
//        router.route().method(HttpMethod.GET).handler(ctx->{
//    ctx.response().putHeader("content-type", "application/json").end(new JsonObject().put("data",Thread.currentThread().getName()).encodePrettily());
//        });

//        router.route().method(HttpMethod.GET).path("/discovery").handler(ha->{
//            TimeoutHandler.create(5000,503);
//          vertx.executeBlocking(h->{
//              try{Thread.sleep(5000);
//                  ha.end();}
//              catch (Exception e){
//                  LOG.error("ERROR"+e.getMessage());
//              }
//
//          });
//        });

        router.route("/discovery")
                .putMetadata("metadata-key", "123")
                .handler(ctx -> {
                    Route route = ctx.currentRoute();
                    String value = route.getMetadata("metadata-key"); // 123
                    // will end the request with the value 123
                    ctx.end(value);
                });
//       Handle exceptions
       vertx.createHttpServer().requestHandler(router).exceptionHandler(exception -> {
            LOG.error("Exception Occurred" + ":" + exception.getCause().getMessage());
        }).listen(8080, http -> {
            if (http.succeeded()) {
                startPromise.complete();
                LOG.debug("HTTP server started");
            } else {
                startPromise.fail(http.cause());
                LOG.error("HTTP server not started :" + http.cause());
            }

        });
    }
}
