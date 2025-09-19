package br.com.paulopinheiro.onlinestore.core.facades.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.SupportTicket;
import br.com.paulopinheiro.onlinestore.core.facades.HelpDeskFacade;
import br.com.paulopinheiro.onlinestore.persistence.utils.comparators.CustomSupportTicketsComparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultHelpDeskFacade implements HelpDeskFacade {

    private final Queue<SupportTicket> tickets;

    {
        tickets = new PriorityQueue<>(new CustomSupportTicketsComparator());
    }

    @Override
    public void addNewSupportTicket(SupportTicket supportTicket) {
        tickets.offer(supportTicket);
    }

    @Override
    public SupportTicket getNextSupportTicket() {
        return tickets.poll();
    }

    @Override
    public int getNumberOfTickets() {
        return tickets.size();
    }
}
