package br.com.paulopinheiro.onlinestore.core.facades;

import br.com.paulopinheiro.onlinestore.persistence.entities.SupportTicket;

public interface HelpDeskFacade {
    void addNewSupportTicket(SupportTicket supportTicket);
    SupportTicket getNextSupportTicket();
    int getNumberOfTickets();
}
