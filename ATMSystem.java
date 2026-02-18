import java.util.Scanner;

/* ---------- BANK ACCOUNT CLASS ---------- */
class BankAccount {
    private double balance;

    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful!");
        }
    }

    double getBalance() {
        return balance;
    }
}

/* ---------- ATM CLASS ---------- */
class ATM {
    private BankAccount account;
    private Scanner sc = new Scanner(System.in);

    ATM(BankAccount account) {
        this.account = account;
    }

    void start() {
        while (true) {
            System.out.println("\n----- ATM MACHINE -----");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    account.withdraw(w);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    account.deposit(d);
                    break;

                case 3:
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using ATM!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

/* ---------- MAIN CLASS ---------- */
public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000); // initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
