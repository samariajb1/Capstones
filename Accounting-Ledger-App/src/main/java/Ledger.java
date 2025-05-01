import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ledger {

    public static ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        // Sort newest first: most recent date/time at top
        Collections.sort(transactions, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                int dateCompare = t2.getDate().compareTo(t1.getDate());
                if (dateCompare == 0) {
                    return t2.getTime().compareTo(t1.getTime());
                }
                return dateCompare;
            }
        });

        return transactions;
    }

    public static void displayDeposits() {
        ArrayList<Transaction> transactions = getAllTransactions();
        System.out.println("\n--- Deposits ---");
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public static void displayPayments() {
        ArrayList<Transaction> transactions = getAllTransactions();
        System.out.println("\n--- Payments ---");
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public static void displayAllTransactions() {
        ArrayList<Transaction> transactions = getAllTransactions();
        System.out.println("\n--- All Transactions ---");
        for (Transaction t : transactions) {
            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
        }
    }

    public static void displayReports() {
        ArrayList<Transaction> transactions = getAllTransactions();
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            double amt = t.getAmount();
            if (amt > 0) {
                totalDeposits += amt;
            } else {
                totalPayments += amt; // amt is negative already
            }
        }

        double netTotal = totalDeposits + totalPayments;

        System.out.println("\n--- Reports ---");
        System.out.printf("Total Deposits: $%.2f\n", totalDeposits);
        System.out.printf("Total Payments: $%.2f\n", Math.abs(totalPayments));
        System.out.printf("Net Balance: $%.2f\n", netTotal);
    }
}