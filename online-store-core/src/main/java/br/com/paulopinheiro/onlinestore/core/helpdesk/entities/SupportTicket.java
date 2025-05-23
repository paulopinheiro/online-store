package br.com.paulopinheiro.onlinestore.core.helpdesk.entities;

public interface SupportTicket {
    Priority getPriority();
    int getSequentialNumber();
    RequestType getRequestType();
}
