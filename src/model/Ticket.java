package model;

public class Ticket implements Comparable<Ticket> {
    private String customerName;
    private String issueDescription;
    private String priority; // "High", "Medium", "Low"
    private long arrivalTime; // milliseconds or incremented counter

    public Ticket(String customerName, String issueDescription, String priority, long arrivalTime) {
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
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

