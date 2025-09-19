package br.com.paulopinheiro.onlinestore.persistence.entities;

import br.com.paulopinheiro.onlinestore.persistence.entities.Priority;
import br.com.paulopinheiro.onlinestore.persistence.entities.RequestType;

public interface SupportTicket {
    Priority getPriority();
    int getSequentialNumber();
    RequestType getRequestType();
}
