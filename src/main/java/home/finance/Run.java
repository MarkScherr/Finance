package home.finance;

import home.finance.config.JdbcConfig;
import home.finance.innerapps.BillApplication;
import home.finance.innerapps.BillPayCalculatorApplication;
import home.finance.innerapps.IncomeApplication;
import home.finance.innerapps.ReceiptApplication;
import home.finance.repository.BillRepository;
import home.finance.repository.CurrentTotalsRepository;
import home.finance.repository.IncomeRepository;
import home.finance.repository.ReceiptRepository;
import home.finance.service.BillPayCalculatorService;
import home.finance.service.BillService;
import home.finance.service.IncomeService;
import home.finance.service.ReceiptService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Run {

    public static void startApplication() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        JdbcTemplate jdbcTemplate = JdbcConfig.createJdbcTemplate();
        BillRepository billRepository = new BillRepository(jdbcTemplate);
        BillService billService = new BillService(billRepository);
        BillApplication billApplication = new BillApplication(billService);
        IncomeRepository incomeRepository = new IncomeRepository(jdbcTemplate);
        IncomeService incomeService = new IncomeService(incomeRepository);
        IncomeApplication incomeApplication = new IncomeApplication(incomeService);
        ReceiptRepository receiptRepository = new ReceiptRepository(jdbcTemplate);
        ReceiptService receiptService = new ReceiptService(receiptRepository);
        ReceiptApplication receiptApplication = new ReceiptApplication(receiptService);
        CurrentTotalsRepository currentTotalsRepository = new CurrentTotalsRepository(jdbcTemplate);
        BillPayCalculatorService billPayCalculatorService = new BillPayCalculatorService(billRepository,
                incomeRepository, receiptRepository, currentTotalsRepository);
        BillPayCalculatorApplication billPayCalculatorApplication = new BillPayCalculatorApplication(billPayCalculatorService);

        while (true) {
//            receiptApplication.run();
            System.out.println("Please choose an option:");
            System.out.println("1. Bill");
            System.out.println("2. Income");
            System.out.println("3. Receipt");
            System.out.println("4. Bill Pay Date Calculator");
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
                case "4":
                    billPayCalculatorApplication.run();
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
