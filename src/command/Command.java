package command;

public class Command {
    private String type;  // new, resolve, display, history
    private String customerName;
    private String issueDescription;
    private String priority;

    public Command(String type, String customerName, String issueDescription, String priority) {
        this.type = type;
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
    }

    public Command(String type) {
        this(type, null, null, null);
    }

    public String getType() {
        return type;
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
}
