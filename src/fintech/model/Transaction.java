package fintech.model;

public abstract class Transaction {

    protected int id;
    protected String username;
    protected double amount;
    protected String timestamp;
    protected String description;

    public Transaction(int id, String username, double amount, String timestamp, String description) {
        this.id = id;
        this.username = username;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public abstract String getType();

    // Getter methods
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}