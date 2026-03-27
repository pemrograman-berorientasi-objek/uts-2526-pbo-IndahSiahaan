package fintech.driver;
 
import fintech.model.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 12S24052 Indah Triyuni Siahaan
 */
public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
 
        String line = scanner.nextLine();
        while (!line.equals("---")) {
            String[] parts = line.split("#");
            if (parts[0].equals("create-account")) {
                accounts.add(new Account(parts[1], parts[2]));
            }
            line = scanner.nextLine();
        }
 
        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getBalance());
        }
    }
}
 