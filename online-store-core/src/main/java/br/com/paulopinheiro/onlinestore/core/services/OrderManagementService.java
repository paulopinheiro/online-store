package br.com.paulopinheiro.onlinestore.core.services;

import br.com.paulopinheiro.onlinestore.persistence.entities.Order;
import java.util.List;

public interface OrderManagementService {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
    List<Order> getOrders();
}
