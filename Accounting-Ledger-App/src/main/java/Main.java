// All Imports I need
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


// main classs
public class Main {
    // my tools
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Transaction> accounts = new ArrayList<Transaction>();
    public static HashMap<String,Transaction> transactions = new HashMap<String,Transaction>();
// this is where I can run my program
    public static void main(String[] args) {

        System.out.print("Welcome to DS Bank!");
        System.out.println("");


        while(true) {
            System.out.println("Please Pick A Selection (1-4)");
            System.out.println("\t1- I am making a deposit\n" + "\t2- I am making a payment\n" + "\t3- I want to view my transactions\n" + "\t4- Press 4 to Exit");
            System.out.print("Enter Here: ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1 :
                    System.out.print("What is the date of your deposit? (MM/DD/YYYY): ");
                    String date = scanner.nextLine();

                    System.out.print("What is the time of your deposit? (HH:MM:SS): ");
                    String time = scanner.nextLine();

                    System.out.print("Describe Your Deposit: ");
                    String description = scanner.nextLine();

                    System.out.print("What is the vendor name?: ");
                    String vendor = scanner.nextLine();

                    System.out.print("Please Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    //now im creating a transaction deposit to save
                    Transaction deposit = new Transaction(date,time,description,vendor,amount);
                    try {
                        deposit.saveTransaction();
                        System.out.println(" Deposit Saved!");
                        scanner.nextLine();

                    } catch (IOException e) {
                        System.out.println(" Sorry, Something went wrong with your deposit :(");
                    }
                    System.out.println("Press Enter to return to the Home Menu...");
                    scanner.nextLine(); // waits for Enter key
                    break;



                case 2:
                    System.out.print("What is the date of your payment?: ");
                    String payDate = scanner.nextLine();

                    System.out.print("What is the time of your payment? (HH:MM:SS): ");
                    String payTime = scanner.nextLine();

                    System.out.print("Enter a short description: ");
                    String payDescription = scanner.nextLine();

                    System.out.print("Who is the vendor?: ");
                    String payVendor = scanner.nextLine();

                    System.out.print("How much are you paying?: ");
                    double payAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Transaction payment = new Transaction(payDate, payTime, payDescription, payVendor, -Math.abs(payAmount));
                    try {
                        payment.saveTransaction();
                        System.out.println("Payment recorded! Returning to Home...");
                    } catch (IOException e) {
                        System.out.println("Something went wrong while saving your payment.");
                    }
                    System.out.println("Press Enter to return to the Home Menu...");
                    scanner.nextLine(); // waits for Enter key
                    try {
                        Thread.sleep(2000); // wait 2 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Show ledger menu
                    boolean inLedger = true;
                    while (inLedger) {
                        System.out.println("You're in the ledger!");
                        System.out.println("\t1- See all entries");
                        System.out.println("\t2- Deposits Only");
                        System.out.println("\t3- Payments Only");
                        System.out.println("\t4- Reports");
                        System.out.println("\t5- Home");
                        System.out.print("Enter selection: ");
                        int ledgerSelection = scanner.nextInt();
                        scanner.nextLine();

                        switch (ledgerSelection) {
                            case 1:
                                Ledger.displayAllTransactions();
                                break;
                            case 2:
                                Ledger.displayDeposits();
                                break;
                            case 3:
                                Ledger.displayPayments();
                                break;
                            case 4:
                                Ledger.displayReports(); // implement this method
                                break;
                            case 5:
                                inLedger = false;
                                break;
                            default:
                                System.out.println("Invalid selection.");
                        }
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    System.exit(0); // Ends the program
                    break;

                default:
                    System.out.println("Invalid selection. Please choose between 1 and 4.");
            }



            }
        }
    }
