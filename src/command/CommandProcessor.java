package command;

import model.*;
import java.util.*;

public class CommandProcessor {

    // active tickets, not resolved yet
    private GenericPriorityQueue<Ticket> activeTickets;

    // resolved, closed tickets
    private GenericHistory<Ticket> resolvedTickets;

    public CommandProcessor() {
        activeTickets = new GenericPriorityQueue<>();
        resolvedTickets = new GenericHistory<>();
    }

    // run commands one by one
    public void process(List<Command> commands) {
        for (Command cmd : commands) {
            String type = cmd.getType().toLowerCase(); 
            // note: to avoid case sensitivity

            switch (type) {
                case "new":
                    // create new ticket
                    Ticket ticket = new Ticket(
                        cmd.getCustomerName(),
                        cmd.getIssueDescription(),
                        cmd.getPriority(),
                        System.currentTimeMillis()
                        // not: current time
                    );
                    System.out.println("Adding Ticket: " + ticket.getCustomerName()
                    + " - " + ticket.getIssueDescription()
                    + " [" + ticket.getPriority() + " Priority]");
                    activeTickets.offer(ticket); 
                    // not: add queue with priorities
                    break;

                case "resolve":
                    // remove from active
                    Ticket resolved = activeTickets.poll();
                    // not: remove the highest
                    if (resolved != null) {
                    	System.out.println("\nResolving Ticket:");
                        System.out.println("Resolved: " + resolved);
                        resolvedTickets.add(resolved); 
                        // not: resolved tickets go to history
                    } else {
                        System.out.println("No tickets to resolve.");
                    }
                    break;

                case "display":
                    // show active tickets
                    List<Ticket> list = activeTickets.getAll();
                    String mode = cmd.getDisplayMode();

                    if ("priority".equals(mode)) {
                        System.out.println("\n--- Displaying Active Tickets (By Priority) ---");
                        Collections.sort(list); // Ticket implements Comparable by priority
                    } else if ("desc".equals(mode)) {
                        System.out.println("\n--- Displaying Active Tickets (By DESC - Newest First) ---");
                        list.sort(Comparator.comparingLong(Ticket::getArrivalTime).reversed());
                    } else {
                        System.out.println("\n--- Displaying Active Tickets (By ASC - Oldest First) ---");
                        list.sort(Comparator.comparingLong(Ticket::getArrivalTime));
                    }

                    printList(list);
                    break;

                    
                case "history":
                	
                    List<Ticket> historyList = resolvedTickets.getAll();
                    String histMode = cmd.getDisplayMode();

                    if (histMode == null) {
                        System.out.println("\n--- Resolved Ticket History (Sorted by Customer Name) ---");
                        historyList.sort(new TicketNameComparator());
                    } else if ("desc".equals(histMode)) {
                        System.out.println("\n--- Resolved Ticket History (DESC - Newest First) ---");
                        historyList.sort(Comparator.comparingLong(Ticket::getArrivalTime).reversed());
                    } else {
                        System.out.println("\n--- Resolved Ticket History (ASC - Oldest First) ---");
                        historyList.sort(Comparator.comparingLong(Ticket::getArrivalTime));
                    }

                    printList(historyList);
                    break;


                default:
                    // not valid
                    System.out.println("Unknown command: " + type);
                    // not: warn me if unknown command
            }
        }
    }

    // show tickets with numbers
    private void printList(List<Ticket> tickets) {
        int i = 1;
        for (Ticket t : tickets) {
            System.out.println(i++ + ". " + t);
        }
    }
}

