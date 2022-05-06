package com.personal.javafilestwo.vertex.web.forms;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class FormExampler extends AbstractVerticle {
    public static void main(String[] args) {
        var vertx = Vertx.vertx();
        vertx.deployVerticle("com.personal.javafilestwo.vertex.web.forms.FormExampler");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        // Enable multipart form data parsing
        router.route().handler(BodyHandler.create());

        router.route().path("/discovery").handler(ctx->{
            ctx.response().putHeader("content-type", "text/html").end(
                    "<form action=\"/form\" method=\"post\">\n" +
                            "    <div>\n" +
                            "        <label for=\"name\">Enter your name:</label>\n" +
                            "        <input type=\"text\" id=\"name\" name=\"name\" />\n" +
                            "    </div>\n" +
                            "    <div class=\"button\">\n" +
                            "        <button type=\"submit\">Send</button>\n" +
                            "    </div>" +
                            "</form>"
            );
        });
        vertx.createHttpServer().requestHandler(router).listen(8080);
// handle the form
        router.post("/form").handler(ctx -> {
            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
            // note the form attribute matches the html form element name.
            ctx.response().end("Hello " + ctx.request().getParam("name") + "!");
        });
        startPromise.complete();
    }
}
