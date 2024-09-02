package home.finance.innerapps;

import home.finance.entity.dao.Bill;
import home.finance.repository.BillRepository;
import home.finance.service.BillService;

import java.util.List;
import java.util.Scanner;

public class BillApplication {

    private final BillRepository billRepository;

    public BillApplication(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        BillService billService = new BillService(billRepository);
        boolean stayInLevel = true;
        while (stayInLevel) {
            String  userInput = "";
            // Print menu options
            System.out.println("\nBILLS BILLS BILLS!!!!!!!!!!!!\n--------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. list bills");
            System.out.println("2. add bill");
            System.out.println("3. update bill");
            System.out.println("4. remove bill");
            System.out.println("5. display bill");
            System.out.println("8. up");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            while (userInput.isBlank())  {
                userInput = scanner.nextLine();
            }

            // Process the input
            switch (userInput) {
                case "1":
                    List<Bill> bills = billService.findAllBills();
                    for (Bill bill : bills) {
                        System.out.println(bill.getBillName());
                    }
                    break;
                case "2":
                    billService.addBill();
                    break;
                case "3":
                    billService.updateBill();
                    break;
                case "4":
                    billService.removeBill(scanner);
                    break;
                case "5":
                    billService.displayBill(scanner);
                    break;
                case "8":
                    System.out.println("peace out billie bobs!\n\n\n");
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
