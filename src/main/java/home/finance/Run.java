package home.finance;

import home.finance.config.JdbcConfig;
import home.finance.innerapps.BillApplication;
import home.finance.innerapps.IncomeApplication;
import home.finance.repository.BillRepository;
import home.finance.repository.IncomeRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Run {

    public static void startApplication() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        JdbcTemplate jdbcTemplate = JdbcConfig.createJdbcTemplate();
        BillRepository billRepository = new BillRepository(jdbcTemplate);
        BillApplication billApplication = new BillApplication(billRepository);
        IncomeRepository incomeRepository = new IncomeRepository(jdbcTemplate);
        IncomeApplication incomeApplication = new IncomeApplication(incomeRepository);
        while (true) {
            receiptApplication.run();
            System.out.println("Please choose an option:");
            System.out.println("1. Bill");
            System.out.println("2. Income");
            System.out.println("3. Receipt");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            userInput = scanner.nextLine();

            // Process the input
            switch (userInput) {
                case "1":
                    billApplication.run();
                    break;
                case "2":
                    incomeApplication.run();
                    break;
                case "3":
                    receiptApplication.run();
                    break;
                case "10":
                    System.out.println("\nSmell ya later!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

}
