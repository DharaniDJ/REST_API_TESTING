package com.example.rest_api_testing.pojo;

import java.util.List;

public class Orders {
    private List<OrderDetail> orders;

    public List<OrderDetail> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetail> orders) {
        this.orders = orders;
    }

}
