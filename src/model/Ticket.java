package model;

/**
 * Ticket - Represents a customer service support ticket with priority and arrival time.
 * Implements Comparable to support sorting by priority and FIFO order.
 */

public class Ticket implements Comparable<Ticket> {
    private String customerName;
    private String issueDescription;
    private String priority; // "High", "Medium", "Low"
    private long arrivalTime; // Timestamp or sequential counter

    // Constructs a Ticket with the specified fields.
    public Ticket(String customerName, String issueDescription, String priority, long arrivalTime) {
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }

    // Returns the customer's name.
    public String getCustomerName() {
        return customerName;
    }

    // Returns the issue description.
    public String getIssueDescription() {
        return issueDescription;
    }

    // Returns the ticket's priority.
    public String getPriority() {
        return priority;
    }

    // Returns the ticket's arrival time.
    public long getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Converts priority string to a numeric value.
     * High = 3, Medium = 2, Low = 1.
     */
    private int getPriorityValue() {
        switch (priority.toLowerCase()) {
            case "high":
                return 3;
            case "medium":
                return 2;
            case "low":
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Compares two tickets by priority first, then by arrival time.
     * Used to determine which ticket should be handled first.
     */
    @Override
    public int compareTo(Ticket other) {
        int thisPriority = this.getPriorityValue();
        int otherPriority = other.getPriorityValue();

        if (thisPriority != otherPriority) {
            return otherPriority - thisPriority; // High > Medium > Low
        } else {
            return Long.compare(this.arrivalTime, other.arrivalTime); // FIFO
        }
    }

    // Returns a readable string representation of the ticket.
    @Override
    public String toString() {
        return customerName + " - " + issueDescription + " [" + priority + "]";
    }
}
