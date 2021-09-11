package com.shopping.client;

import com.shopping.stubs.order.Order;
import com.shopping.stubs.order.OrderRequest;
import com.shopping.stubs.order.OrderResponse;
import com.shopping.stubs.order.OrderServiceGrpc;

import io.grpc.Channel;

import java.util.List;
import java.util.logging.Logger;

public class OrderClient {
    //get a stub object, need a HTTP2 connection
    // call the service method
    private final Logger logger = Logger.getLogger(OrderClient.class.getName());
    private final OrderServiceGrpc.OrderServiceBlockingStub  orderServiceBlockingStub;

    public OrderClient(Channel channel) {
        orderServiceBlockingStub = OrderServiceGrpc.newBlockingStub(channel);
    }

    public List<Order> getOrders(int userId) {
        logger.info("OrderClient calling the order service");
        OrderRequest orderRequest = OrderRequest.newBuilder().setUserId(userId).build();
        OrderResponse orderResponse = orderServiceBlockingStub.getOrdersForUser(orderRequest);
        return orderResponse.getOrderList();
    }
}
