package com.shopping.server;

import com.shopping.service.OrderServiceImpl;
import com.shopping.service.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServer {

    private static final Logger logger = Logger.getLogger(UserServer.class.getName());

    private Server server;

    public void startServer() {
        int port = 20021;
        try {
            server = ServerBuilder
                    .forPort(port)
                    .addService(new UserServiceImpl())
                    //.addService(new OrderServiceImpl()) you can do this, but it will run on same port
                    .build()
                    .start();

            logger.info("User Server Started on port " + port);
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public void run() {
                    logger.log(Level.INFO, "Clean server shutdown in case JVM was shutdown");
                    try {
                        UserServer.this.stopServer();
                    } catch (InterruptedException e) {
                        logger.log(Level.SEVERE, "server shutdown Interrupted ", e);
                    }
                }
            });

        } catch (IOException e) {
            logger.log(Level.SEVERE, "UserServer did not start");
        }
    }

    public void stopServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    //TODO:
    // test the service using BloomRPC GUI tool
    // https://appimage.github.io/BloomRPC

    public static void main(String[] args) throws InterruptedException {
        UserServer userServer = new UserServer();
        userServer.startServer();
        userServer.blockUntilShutdown();

        /*
            To Run this application as a standalone
            java -jar <path of jar>
            changes in pom.xml
                add a plugin to create a fat jar
                add the main method class
                add the resource folder to the jar as we have initialize.sql file
         */
    }
}
