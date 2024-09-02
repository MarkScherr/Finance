package home.finance.innerapps;

import home.finance.entity.dao.Income;
import home.finance.repository.IncomeRepository;
import home.finance.service.IncomeService;

import java.util.List;
import java.util.Scanner;

public class IncomeApplication {


    private final IncomeRepository incomeRepository;

    public IncomeApplication(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        IncomeService incomeService = new IncomeService(incomeRepository);
        boolean stayInLevel = true;
        while (stayInLevel) {
            String  userInput = "";
            System.out.println("!!!!!!!!!!!!INCOME!!!!INCOME!!!!INCOME!!!!!!!!!!!!\n--------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. list income");
            System.out.println("2. add income");
            System.out.println("3. update income");
            System.out.println("4. remove income");
            System.out.println("5. display income");
            System.out.println("8. up");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            while (userInput.isBlank())  {
                userInput = scanner.nextLine();
            }

            // Process the input
            switch (userInput) {
                case "1":
                    List<Income> incomes = incomeService.findAllIncomeInDateRange(scanner);
                    if (incomes.isEmpty()) {
                        System.out.println("unable to find any income data");
                    }
                    for (Income income : incomes) {
                        System.out.println(income.getName());
                    }
                    break;
                case "2":
                    incomeService.addIncome(scanner);
                    break;
                case "3":
                    incomeService.updateIncome(scanner);
                    break;
                case "4":
                    incomeService.removeIncome(scanner);
                    break;
                case "5":
                    incomeService.displayIncome(scanner);
                    break;
                case "8":
                    System.out.println("peace out incoming incomes!");
                    stayInLevel = false;
                    break;
                case "9":
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
