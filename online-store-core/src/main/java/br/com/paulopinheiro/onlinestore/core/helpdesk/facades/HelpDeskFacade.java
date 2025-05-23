package br.com.paulopinheiro.onlinestore.core.helpdesk.facades;

import br.com.paulopinheiro.onlinestore.core.helpdesk.entities.SupportTicket;

public interface HelpDeskFacade {
    void addNewSupportTicket(SupportTicket supportTicket);
    SupportTicket getNextSupportTicket();
    /**
     * @return amount of tickets that are not processed
     */
    int getNumberOfTickets();
}
