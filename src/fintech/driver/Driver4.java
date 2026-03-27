package fintech.driver;
 
import fintech.model.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author 12S24052 Indah Triyuni Siahaan
 */
public class Driver4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        List<String> showRequests = new ArrayList<>();
        int transactionCounter = 1;
 
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
                    String timestamp = parts[3];
                    String description = parts[4];

                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(username)) {
                            acc.deposit(amount);
                            transactions.add(new DepositTransaction(
                                transactionCounter++, username, amount, timestamp, description));
                            break;
                        }
                    }
                    break;
                }

                case "withdraw": {
                    String username = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String timestamp = parts[3];
                    String description = parts[4];

                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(username)) {
                            try {
                                if (!acc.withdraw(amount)) {
                                    throw new NegativeBalanceException("Saldo tidak cukup");
                                }

                                transactions.add(new WithdrawTransaction(
                                    transactionCounter++, username, -amount, timestamp, description));

                            } catch (NegativeBalanceException e) {
                                // lanjut
                            }
                            break;
                        }
                    }
                    break;
                }

                case "transfer": {
                    String senderUsername = parts[1];
                    String receiverUsername = parts[2];
                    double amount = Double.parseDouble(parts[3]);
                    String timestamp = parts[4];
                    String description = parts[5];

                    Account sender = null, receiver = null;

                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(senderUsername)) sender = acc;
                        if (acc.getUsername().equals(receiverUsername)) receiver = acc;
                    }

                    if (sender != null && receiver != null) {
                        try {
                            if (!sender.withdraw(amount)) {
                                throw new NegativeBalanceException("Saldo tidak cukup");
                            }

                            receiver.deposit(amount);

                            transactions.add(new TransferTransaction(
                                transactionCounter++, senderUsername, receiverUsername,
                                amount, timestamp, description));

                        } catch (NegativeBalanceException e) {
                            // lanjut
                        }
                    }
                    break;
                }

                case "show-account":
                    showRequests.add(parts[1]);
                    break;
            }
            line = scanner.nextLine();
        }

        for (String username : showRequests) {
            for (Account acc : accounts) {
                if (acc.getUsername().equals(username)) {
                    System.out.println(acc.getUsername() + "|" +
                                       acc.getName() + "|" +
                                       acc.getBalance());

                    transactions.stream()
                        .filter(t -> t.getUsername().equals(username))
                        .sorted(Comparator.comparing(Transaction::getTimestamp))
                        .forEach(t -> System.out.println(
                            t.getId() + "|" +
                            t.getType() + "|" +
                            t.getAmount() + "|" +
                            t.getTimestamp() + "|" +
                            t.getDescription()
                        ));
                    break;
                }
            }
        }
    }
}
