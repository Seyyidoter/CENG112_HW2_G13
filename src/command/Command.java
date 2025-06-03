package command;

// Command info holder
public class Command {

    private String type;              // Command type
    private String customerName;      // Name
    private String issueDescription;  // Problem
    private String priority;          // High, Medium, Low
    private String displayMode;       // Asc, desc, priority

    // New command
    public Command(String type, String customerName, String issueDescription, String priority) {
        this.type = type;
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        this.displayMode = null;
    }

    // Display or history with mode
    public Command(String type, String displayMode) {
        this.type = type;
        this.customerName = null;
        this.issueDescription = null;
        this.priority = null;
        this.displayMode = displayMode;
    }

    // Resolve, display, history no mode
    public Command(String type) {
        this.type = type;
        this.customerName = null;
        this.issueDescription = null;
        this.priority = null;
        this.displayMode = null;
    }

    // Get type
    public String getType() {
        return type;
    }

    // Get name
    public String getCustomerName() {
        return customerName;
    }

    // Get problem
    public String getIssueDescription() {
        return issueDescription;
    }

    // Get priority
    public String getPriority() {
        return priority;
    }

    // Get display option
    public String getDisplayMode() {
        return displayMode;
    }

    private long arrivalTime;

    /**
     * @return The arrival time timestamp
     */
    public long getArrivalTime() {
        return arrivalTime;
    }

    /**
    * Sets the arrival time for the command
    */
    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}

