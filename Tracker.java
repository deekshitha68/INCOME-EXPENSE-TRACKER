import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tracker {
    private List<Transaction> transactions;
    private static final String FILE_NAME = "transactions.dat";

    public Tracker() {
        transactions = new ArrayList<>();
        loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public void printSummary() {
        double totalIncome = getTotalIncome();
        double totalExpense = getTotalExpense();
        double balance = totalIncome - totalExpense;

        System.out.println("Summary:");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expense: $" + totalExpense);
        System.out.println("Balance: $" + balance);
    }

    public void printTransactions() {
        System.out.println("Date | Type | Amount | Category | Description");
        System.out.println("--------------------------------------------------------");
        transactions.forEach(System.out::println);
    }

    public void printFilteredTransactions(String type) {
        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());

        System.out.println("Filtered Transactions:");
        System.out.println("Date | Type | Amount | Category | Description");
        System.out.println("--------------------------------------------------------");
        filtered.forEach(System.out::println);
    }

    public void printSortedTransactions() {
        List<Transaction> sorted = transactions.stream()
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .collect(Collectors.toList());

        System.out.println("Sorted Transactions:");
        System.out.println("Date | Type | Amount | Category | Description");
        System.out.println("--------------------------------------------------------");
        sorted.forEach(System.out::println);
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Failed to save transactions: " + e.getMessage());
        }
    }

    private void loadTransactions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous transactions found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load transactions: " + e.getMessage());
        }
    }
}
