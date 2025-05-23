package br.com.paulopinheiro.core.helpdesk.entities;

public interface SupportTicket {
    Priority getPriority();
    int getSequentialNumber();
    RequestType getRequestType();
}
