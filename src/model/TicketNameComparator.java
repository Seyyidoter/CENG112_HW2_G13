package model;

import java.util.Comparator;

/**
 * TicketNameComparator - A comparator for Ticket objects that sorts them
 * alphabetically by customer name, ignoring case differences.
 */

public class TicketNameComparator implements Comparator<Ticket> {
    // Compares two Ticket objects by their customer names in a case-insensitive manner.
    @Override
    public int compare(Ticket t1, Ticket t2) {
        return t1.getCustomerName().compareToIgnoreCase(t2.getCustomerName());
    }
}
