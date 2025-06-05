package command;

import model.*;
import java.util.*;

public class CommandProcessor {

    // Priority queue to store active tickets, automatically sorted by priority and arrival time
    private GenericPriorityQueue<Ticket> activeTickets;

    // History container to store resolved tickets with resolve timestamps
    private GenericHistory<Ticket> resolvedTickets;

    /**
     * Constructor - Initializes the command processor with empty collections
     */
    public CommandProcessor() {
        activeTickets = new GenericPriorityQueue<>();
        resolvedTickets = new GenericHistory<>();
    }

    /**
     * Main processing method that handles a list of commands sequentially
     */
    public void process(List<Command> commands) {
        // Iterate through each command and handle based on type
        for (Command cmd : commands) {
            String type = cmd.getType().toLowerCase();

            switch (type) {
                case "new":
                    Ticket ticket = new Ticket(
                            cmd.getCustomerName(),
                            cmd.getIssueDescription(),
                            cmd.getPriority(),
                            cmd.getArrivalTime()
                    );

                    System.out.println("Adding Ticket: " + ticket.getCustomerName()
                            + " - " + ticket.getIssueDescription()
                            + " [" + ticket.getPriority() + " Priority]");
                    activeTickets.offer(ticket);
                    break;

                case "resolve":
                    Ticket resolved = activeTickets.poll();
                    if (resolved != null) {
                        System.out.println("\nResolving Ticket:");
                        System.out.println("Resolved: " + resolved);
                        resolvedTickets.add(resolved);
                    } else {
                        System.out.println("No tickets to resolve.");
                    }
                    break;

                case "display":
                    handleDisplayCommand(cmd);
                    break;

                case "history":
                    handleHistoryCommand(cmd);
                    break;

                default:
                    System.out.println("Unknown command: " + type);
            }
        }
    }

    /**
     * Handles display commands for active tickets with different sorting modes
     */
    private void handleDisplayCommand(Command cmd) {
        List<Ticket> list;
        String mode = cmd.getDisplayMode();

        if ("priority".equals(mode)) {
            list = activeTickets.getAll();
            System.out.println("\n--- Displaying Active Tickets (By Priority) ---");
        } else if ("desc".equals(mode)) {
            list = activeTickets.getAllInArrivalOrder();
            list.sort(Comparator.comparingLong(Ticket::getArrivalTime).reversed());
            System.out.println("\n--- Displaying Active Tickets (By DESC - Newest First) ---");
        } else { // "asc" or null
            list = activeTickets.getAllInArrivalOrder();
            System.out.println("\n--- Displaying Active Tickets (By ASC - Oldest First) ---");
        }

        printList(list);
    }

    /**
     * Handles history commands for resolved tickets with different sorting modes
     */
    private void handleHistoryCommand(Command cmd) {
        List<Ticket> historyList = resolvedTickets.getAll();
        String histMode = cmd.getDisplayMode();

        if (histMode == null) {
            System.out.println("\n--- Resolved Ticket History (Sorted by Customer Name) ---");
            historyList.sort((t1, t2) -> t1.getCustomerName().compareToIgnoreCase(t2.getCustomerName()));
        } else if ("desc".equals(histMode)) {
            System.out.println("\n--- Resolved Ticket History (DESC - Newest First) ---");
            historyList.sort(Comparator.comparingLong(Ticket::getResolveTime).reversed());
        } else { // "asc"
            System.out.println("\n--- Resolved Ticket History (ASC - Oldest First) ---");
            historyList.sort(Comparator.comparingLong(Ticket::getResolveTime));
        }

        printList(historyList);
    }

    /**
     * Utility method to print a numbered list of tickets
     */
    private void printList(List<Ticket> tickets) {
        int i = 1;
        for (Ticket t : tickets) {
            System.out.println(i++ + ". " + t);
        }
    }
}
