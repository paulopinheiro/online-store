package br.com.paulopinheiro.core.storage;

import br.com.paulopinheiro.persistence.entities.Order;
import java.util.List;

public interface OrderStoringService {
    void saveOrders(List<Order> orders);
    List<Order> loadOrders();
}
