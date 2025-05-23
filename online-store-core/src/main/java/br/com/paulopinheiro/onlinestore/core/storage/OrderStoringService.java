package br.com.paulopinheiro.onlinestore.core.storage;

import br.com.paulopinheiro.onlinestore.persistence.entities.Order;
import java.util.List;

public interface OrderStoringService {
    void saveOrders(List<Order> orders);
    List<Order> loadOrders();
}
