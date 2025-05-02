import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Ledger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

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

        Collections.sort(transactions, new Comparator<Transaction>() { //
            public int compare(Transaction t1, Transaction t2) { // Special Piece
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
        Scanner scanner = new Scanner(System.in);
        boolean inReports = true;

        while (inReports) {
            System.out.println("\n--- Reports ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayMonthToDate();
                    break;
                case 2:
                    displayPreviousMonth();
                    break;
                case 3:
                    displayYearToDate();
                    break;
                case 4:
                    displayPreviousYear();
                    break;
                case 5:
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    searchByVendor(vendor);
                    break;
                case 0:
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static void displayMonthToDate() {
        ArrayList<Transaction> transactions = getAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);

        System.out.println("\n--- Month To Date ---");
        for (Transaction t : transactions) {
            LocalDate tDate = parseDate(t.getDate());
            if (!tDate.isBefore(start) && !tDate.isAfter(today)) {
                System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public static void displayPreviousMonth() {
        ArrayList<Transaction> transactions = getAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate firstDayThisMonth = today.withDayOfMonth(1);
        LocalDate lastDayPrevMonth = firstDayThisMonth.minusDays(1);
        LocalDate firstDayPrevMonth = lastDayPrevMonth.withDayOfMonth(1);

        System.out.println("\n--- Previous Month ---");
        for (Transaction t : transactions) {
            LocalDate tDate = parseDate(t.getDate());
            if (!tDate.isBefore(firstDayPrevMonth) && !tDate.isAfter(lastDayPrevMonth)) {
                System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public static void displayYearToDate() {
        ArrayList<Transaction> transactions = getAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfYear(1);

        System.out.println("\n--- Year To Date ---");
        for (Transaction t : transactions) {
            LocalDate tDate = parseDate(t.getDate());
            if (!tDate.isBefore(start) && !tDate.isAfter(today)) {
                System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public static void displayPreviousYear() {
        ArrayList<Transaction> transactions = getAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusYears(1).withDayOfYear(1);
        LocalDate end = today.minusYears(1).with(TemporalAdjusters.lastDayOfYear());

        System.out.println("\n--- Previous Year ---");
        for (Transaction t : transactions) {
            LocalDate tDate = parseDate(t.getDate());
            if (!tDate.isBefore(start) && !tDate.isAfter(end)) {
                System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }

    public static void searchByVendor(String vendorName) {
        ArrayList<Transaction> transactions = getAllTransactions();

        System.out.println("\n--- Transactions for Vendor: " + vendorName + " ---");
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }
    }
}