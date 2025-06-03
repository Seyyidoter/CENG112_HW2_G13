package model;

/**
 * Ticket - Represents a customer service support ticket with priority and arrival time.
 * Implements Comparable to support sorting by priority and FIFO order.
 */
public class Ticket implements Comparable<Ticket> {
    private String customerName;
    private String issueDescription;
    private String priority;
    private long arrivalTime;
    private long resolveTime; // Çözülme sırası için

    public Ticket(String customerName, String issueDescription, String priority, long arrivalTime) {
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.resolveTime = 0;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getPriority() {
        return priority;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(long resolveTime) {
        this.resolveTime = resolveTime;
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

    @Override
    public String toString() {
        return customerName + " - " + issueDescription + " [" + priority + "]";
    }
}
