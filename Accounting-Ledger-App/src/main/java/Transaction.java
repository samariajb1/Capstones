import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//Here is the encapsulation to store info and bring about
public class Transaction {
    private String date,time,description,vendor;
    private double amount;
//My constructor...transaction object
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    // New object to save transactions
    public void saveTransaction() throws IOException {
        //the file is being written into
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true)); // "true" appends to file
        String transactionLine = date + "|" + time + "|" + description + "|" + vendor + "|" + amount; // This is how the file should be written.
        writer.write(transactionLine); // its now formatted and created for user
        writer.newLine(); // moves to next line after writing
        writer.close(); // nothing more
    }
// getters and setters      `
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}



