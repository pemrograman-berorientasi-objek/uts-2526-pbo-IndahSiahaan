package fintech.driver;
 
import fintech.model.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 12S24052 Indah Triyuni Siahaan
 */
public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
 
        String line = scanner.nextLine();
        while (!line.equals("---")) {
            String[] parts = line.split("#");
            switch (parts[0]) {
                case "create-account":
                    accounts.add(new Account(parts[1], parts[2]));
                    break;

                case "deposit": {
                    String username = parts[1];
                    double amount = Double.parseDouble(parts[2]);

                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(username)) {
                            acc.deposit(amount);
                            break;
                        }
                    }
                    break;
                }

                case "withdraw": {
                    String username = parts[1];
                    double amount = Double.parseDouble(parts[2]);

                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(username)) {
                            acc.withdraw(amount);
                            break;
                        }
                    }
                    break;
                }

                case "transfer": {
                    String senderUsername = parts[1];
                    String receiverUsername = parts[2];
                    double amount = Double.parseDouble(parts[3]);

                    Account sender = null, receiver = null;

                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(senderUsername)) sender = acc;
                        if (acc.getUsername().equals(receiverUsername)) receiver = acc;
                    }

                    if (sender != null && receiver != null) {
                        boolean success = sender.withdraw(amount);
                        if (success) {
                            receiver.deposit(amount);
                        }
                    }
                    break;
                }
            }
            line = scanner.nextLine();
        }
 
        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" +
                               acc.getName() + "|" +
                               acc.getBalance());
        }
    }
}