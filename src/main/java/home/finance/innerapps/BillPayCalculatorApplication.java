package home.finance.innerapps;

import home.finance.entity.dao.Receipt;
import home.finance.repository.BillRepository;
import home.finance.repository.IncomeRepository;
import home.finance.repository.ReceiptRepository;
import home.finance.service.BillPayCalculatorService;
import home.finance.service.BillService;
import home.finance.service.IncomeService;
import home.finance.service.ReceiptService;

import java.util.List;
import java.util.Scanner;

public class BillPayCalculatorApplication {
    private final BillPayCalculatorService billPayCalculatorService;

    public BillPayCalculatorApplication(BillPayCalculatorService billPayCalculatorService) {
        this.billPayCalculatorService = billPayCalculatorService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean stayInLevel = true;
        while (stayInLevel) {
            String  userInput = "";
            System.out.println("!!!!!!!!!!!!CALCULATIONS!!!!!!!!!!!!\n--------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. determine monthly payments with dates");
            System.out.println("9. up");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            while (userInput.isBlank())  {
                userInput = scanner.nextLine();
            }

            // Process the input
            switch (userInput) {
                case "1":
                        billPayCalculatorService.determineMonthlyPayments(scanner);
                    break;
                case "9":
                    System.out.println("peace out calculon!");
                    stayInLevel = false;
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
