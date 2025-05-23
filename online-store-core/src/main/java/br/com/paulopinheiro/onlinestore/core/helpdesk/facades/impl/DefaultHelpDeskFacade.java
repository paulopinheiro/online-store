package br.com.paulopinheiro.onlinestore.core.helpdesk.facades.impl;

import br.com.paulopinheiro.onlinestore.core.helpdesk.entities.SupportTicket;
import br.com.paulopinheiro.onlinestore.core.helpdesk.facades.HelpDeskFacade;
import br.com.paulopinheiro.onlinestore.core.helpdesk.utils.CustomSupportTicketsComparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultHelpDeskFacade implements HelpDeskFacade {

    private Queue<SupportTicket> tickets;

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
