import java.util.Scanner;

public class Main {
    private Tracker tracker;

    public Main() {
        tracker = new Tracker();
        run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nIncome & Expense Tracker");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. View Summary");
            System.out.println("5. Filter Income");
            System.out.println("6. Filter Expense");
            System.out.println("7. Sort Transactions");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addTransaction(scanner, "Income");
                    break;
                case 2:
                    addTransaction(scanner, "Expense");
                    break;
                case 3:
                    tracker.printTransactions();
                    break;
                case 4:
                    tracker.printSummary();
                    break;
                case 5:
                    tracker.printFilteredTransactions("Income");
                    break;
                case 6:
                    tracker.printFilteredTransactions("Expense");
                    break;
                case 7:
                    tracker.printSortedTransactions();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private void addTransaction(Scanner scanner, String type) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Transaction transaction = new Transaction(type, amount, category, description);
        tracker.addTransaction(transaction);
        System.out.println(type + " added successfully!");
    }

    public static void main(String[] args) {
        new Main();
    }
}
