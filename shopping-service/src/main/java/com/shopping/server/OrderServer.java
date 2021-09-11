package com.shopping.server;

import com.shopping.service.OrderServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderServer {

    private static final Logger logger = Logger.getLogger(OrderServer.class.getName());

    private Server server;

    public void startServer() {
        int port = 20022;

        try {
            server = ServerBuilder
                    .forPort(port)
                    .addService(new OrderServiceImpl())
                    .build()
                    .start();

            logger.info("Order Server Started on port " + port);
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public void run() {
                    logger.log(Level.INFO, "Clean server shutdown in case JVM was shutdown");
                    try {
                        OrderServer.this.stopServer();
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

    public static void main(String[] args) throws InterruptedException {
        OrderServer orderServer = new OrderServer();
        orderServer.startServer();
        orderServer.blockUntilShutdown();

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
