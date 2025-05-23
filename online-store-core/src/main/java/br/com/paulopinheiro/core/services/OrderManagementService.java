package br.com.paulopinheiro.core.services;

import br.com.paulopinheiro.persistence.entities.Order;
import java.util.List;

public interface OrderManagementService {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
    List<Order> getOrders();
}
