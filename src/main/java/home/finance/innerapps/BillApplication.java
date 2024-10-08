package home.finance.innerapps;

import home.finance.entity.dao.Bill;
import home.finance.service.BillService;

import java.util.List;
import java.util.Scanner;

public class BillApplication {

    private final BillService billService;

    public BillApplication(BillService billService) {
        this.billService = billService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean stayInLevel = true;
        while (stayInLevel) {
            System.out.println("\nBILLS BILLS BILLS!!!!!!!!!!!!\n--------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. list bills");
            System.out.println("2. list unpaid bills");
            System.out.println("3. add bill");
            System.out.println("4. update bill");
            System.out.println("5. remove bill");
            System.out.println("6. display bill");
            System.out.println("8. up");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            String  userInput = "";
            while (userInput.isBlank())  {
                userInput = scanner.nextLine();
            }

            // Process the input
            switch (userInput) {
                case "1":
                    List<Bill> bills = billService.findAllBills();
                    for (Bill bill : bills) {
                        System.out.println(bill.getPaymentDate() + " : " + bill.getBillName() + " : " +
                                bill.getMinimumPaymentAmount());
                    }
                    break;
                case "2":
                    billService.findAllUnpaidBills();
                    break;
                case "3":
                    billService.addBill();
                    break;
                case "4":
                    billService.updateBill();
                    break;
                case "5":
                    billService.removeBill(scanner);
                    break;
                case "6":
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
