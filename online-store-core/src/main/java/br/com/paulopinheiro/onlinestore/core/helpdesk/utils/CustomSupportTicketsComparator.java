package br.com.paulopinheiro.onlinestore.core.helpdesk.utils;

import br.com.paulopinheiro.onlinestore.core.helpdesk.entities.SupportTicket;
import java.util.Comparator;

public class CustomSupportTicketsComparator implements Comparator<SupportTicket> {

    @Override
    public int compare(SupportTicket ticket1, SupportTicket ticket2) {
        if (ticket1 == null || ticket2 == null || ticket1.getPriority() == null
                || ticket2.getPriority() == null) {
            return 0;
        }

        int result = ticket2.getPriority().compareTo(ticket1.getPriority());
        if (result == 0) {
            result = ticket1.getSequentialNumber() - ticket2.getSequentialNumber();
        }
        return result;
    }
}
