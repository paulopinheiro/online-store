package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.OrderManagementService;
import br.com.paulopinheiro.onlinestore.core.storage.OrderStoringService;
import br.com.paulopinheiro.onlinestore.core.storage.impl.DefaultOrderStoringService;
import br.com.paulopinheiro.onlinestore.persistence.entities.Order;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultOrderManagementService implements OrderManagementService {

    private static DefaultOrderManagementService instance;
    private List<Order> orders;
    private OrderStoringService orderStoringService;

    {
        orderStoringService = DefaultOrderStoringService.getInstance();
        orders = orderStoringService.loadOrders();
    }

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        if (order == null) {
            return;
        }
        orders.add(order);
        orderStoringService.saveOrders(orders);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderStoringService.loadOrders().stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getCustomerId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrders() {
        if (orders == null || orders.size() == 0) {
            orders = orderStoringService.loadOrders();
        }
        return this.orders;
    }

    public void clearServiceState() {
        orders.clear();
    }
}
