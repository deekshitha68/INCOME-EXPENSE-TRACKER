import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
    private String type; // "Income" or "Expense"
    private double amount;
    private String category;
    private String description;
    private Date date;

    public Transaction(String type, double amount, String category, String description) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = new Date();
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%s | %s | %.2f | %s | %s", sdf.format(date), type, amount, category, description);
    }
}
