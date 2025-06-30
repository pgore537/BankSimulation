import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// Account class to represent a bank account
class Account {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolder, String accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        logTransaction("Account created for " + accountHolder);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            logTransaction("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            logTransaction("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for Account: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private void logTransaction(String detail) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        transactionHistory.add(timeStamp + " - " + detail);
    }
}

// Main class with user interaction
public class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create account
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();

        Account myAccount = new Account(name, accNumber);

        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    myAccount.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    myAccount.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Current Balance: $" + myAccount.getBalance());
                    break;

                case 4:
                    myAccount.printTransactionHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using the Bank System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
