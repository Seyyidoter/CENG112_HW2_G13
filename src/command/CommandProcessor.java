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
            // note: to avoid case sensivity

            switch (type) {
                case "new":
                    // create new ticket
                    Ticket ticket = new Ticket(
                        cmd.getCustomerName(),
                        cmd.getIssueDescription(),
                        cmd.getPriority(),
                        System.currentTimeMillis()
                        // not: cureent time
                    );
                    System.out.println("Added: " + ticket);
                    activeTickets.offer(ticket); 
                    // not: add queue with priorities
                    break;

                case "resolve":
                    // remove from active
                    Ticket resolved = activeTickets.poll();
                    // not: remove the highest
                    if (resolved != null) {
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

                    System.out.println("--- Active Tickets (" + mode.toUpperCase() + ") ---");

                    if ("priority".equals(mode)) {
                        Collections.sort(list); 
                        // not: Ticket içinde compareTo var
                    } else if ("desc".equals(mode)) {
                        Collections.reverse(list); 
                        // not: LIFO
                    } // asc: normal order

                    printList(list);
                    break;

                case "history":
                    // show resolved tickets
                    List<Ticket> historyList = resolvedTickets.getAll();
                    String histMode = cmd.getDisplayMode();

                    if (histMode == null) {
                        System.out.println("--- History (by name) ---");
                        historyList.sort(new TicketNameComparator());
                        // not: sort name
                    } else if ("desc".equals(histMode)) {
                        System.out.println("--- History (newest first) ---");
                        Collections.reverse(historyList);
                    } else {
                        System.out.println("--- History (oldest first) ---");
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
