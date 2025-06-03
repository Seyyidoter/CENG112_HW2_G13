package command;

// command info holder
public class Command {

    private String type;              // command type
    private String customerName;      // name
    private String issueDescription;  // problem
    private String priority;          // High, Medium, Low
    private String displayMode;       // asc, desc, priority

    // new command
    public Command(String type, String customerName, String issueDescription, String priority) {
        this.type = type;
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        this.displayMode = null;
    }

    // display or history with mode
    public Command(String type, String displayMode) {
        this.type = type;
        this.customerName = null;
        this.issueDescription = null;
        this.priority = null;
        this.displayMode = displayMode;
    }

    // resolve, display, history no mode
    public Command(String type) {
        this.type = type;
        this.customerName = null;
        this.issueDescription = null;
        this.priority = null;
        this.displayMode = null;
    }

    // get type
    public String getType() {
        return type;
    }

    // get name
    public String getCustomerName() {
        return customerName;
    }

    // get problem
    public String getIssueDescription() {
        return issueDescription;
    }

    // get priority
    public String getPriority() {
        return priority;
    }

    // get display option
    public String getDisplayMode() {
        return displayMode;
    }
}
