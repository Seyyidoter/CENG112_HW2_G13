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
                    // Create a new ticket with customer info, issue, priority, and arrival time
                    Ticket ticket = new Ticket(
                            cmd.getCustomerName(),
                            cmd.getIssueDescription(),
                            cmd.getPriority(),
                            cmd.getArrivalTime()
                    );

                    // Print confirmation message and add to active tickets queue
                    System.out.println("\nAdding Ticket: " + ticket.getCustomerName()
                            + " - " + ticket.getIssueDescription()
                            + " [" + ticket.getPriority() + " Priority]");
                    activeTickets.offer(ticket);
                    break;

                case "resolve":
                    // Remove the highest priority ticket from active queue
                    Ticket resolved = activeTickets.poll();
                    if (resolved != null) {
                        // Display resolution info and move to resolved history
                        System.out.println("\nResolving Ticket:");
                        System.out.println("Resolved: " + resolved);
                        resolvedTickets.add(resolved);
                    } else {
                        // No tickets available to resolve
                        System.out.println("No tickets to resolve.");
                    }
                    break;

                case "display":
                    // Handle displaying active tickets with various sorting options
                    handleDisplayCommand(cmd);
                    break;

                case "history":
                    // Handle displaying resolved ticket history with sorting options
                    handleHistoryCommand(cmd);
                    break;

                default:
                    // Unknown command type
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
            // Display by priority order: High->Medium->Low, FIFO within each group
            list = activeTickets.getAll();
            System.out.println("\n--- Displaying Active Tickets (By Priority) ---");
        } else if ("desc".equals(mode)) {
            // Display by arrival time in descending order (newest first)
            list = activeTickets.getAllInArrivalOrder();
            list.sort(Comparator.comparingLong(Ticket::getArrivalTime).reversed());
            System.out.println("\n--- Displaying Active Tickets (By DESC - Newest First) ---");
        } else { // "asc" or null
            // Display by arrival time in ascending order (oldest first)
            list = activeTickets.getAllInArrivalOrder();
            System.out.println("\n--- Displaying Active Tickets (By ASC - Oldest First) ---");
        }

        // Print the formatted list
        printList(list);
    }

    /**
    * Handles history commands for resolved tickets with different sorting modes
    */
    private void handleHistoryCommand(Command cmd) {
        List<Ticket> historyList = resolvedTickets.getAll();
        String histMode = cmd.getDisplayMode();

        if (histMode == null) {
            // Default: Sort alphabetically by customer name
            System.out.println("\n--- Resolved Ticket History (Sorted by Customer Name) ---");
            historyList.sort(new TicketNameComparator());
        } else if ("desc".equals(histMode)) {
            // Sort by resolve time in descending order (most recently resolved first)
            System.out.println("\n--- Resolved Ticket History (DESC - Newest First) ---");
            historyList.sort(Comparator.comparingLong(Ticket::getResolveTime).reversed());
        } else { // "asc"
            // Sort by resolve time in ascending order (first resolved first)
            System.out.println("\n--- Resolved Ticket History (ASC - Oldest First) ---");
            historyList.sort(Comparator.comparingLong(Ticket::getResolveTime));
        }

        // Print the formatted list
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
