package model;

import java.util.Comparator;

public class TicketNameComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        return t1.getCustomerName().compareToIgnoreCase(t2.getCustomerName());
    }
}

