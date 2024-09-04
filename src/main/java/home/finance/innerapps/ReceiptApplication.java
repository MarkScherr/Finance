package home.finance.innerapps;

import home.finance.entity.dao.Receipt;
import home.finance.repository.ReceiptRepository;
import home.finance.service.ReceiptService;

import java.util.List;
import java.util.Scanner;

public class ReceiptApplication {


    private final ReceiptService receiptService;

    public ReceiptApplication(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean stayInLevel = true;
        while (stayInLevel) {
            String  userInput = "";
            System.out.println("!!!!!!!!!!!!RECEIPTS!!!!RECEIPTS!!!!RECEIPTS!!!!!!!!!!!!\n--------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. list receipts");
            System.out.println("2. add receipt");
            System.out.println("3. update receipt");
            System.out.println("4. remove receipt");
            System.out.println("5. display receipt");
            System.out.println("8. up");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            while (userInput.isBlank())  {
                userInput = scanner.nextLine();
            }

            // Process the input
            switch (userInput) {
                case "1":
                    List<Receipt> receipts = receiptService.findAllReceiptInDateRange(scanner);
                    if (receipts.isEmpty()) {
                        System.out.println("unable to find any receipt data");
                    }
                    for (Receipt receipt : receipts) {
                        System.out.println(receipt.getName());
                    }
                    break;
                case "2":
                    receiptService.addReceipt(scanner);
                    break;
                case "3":
                    receiptService.updateReceipt(scanner);
                    break;
                case "4":
                    receiptService.removeReceipt(scanner);
                    break;
                case "5":
                    receiptService.displayReceipt(scanner);
                    break;
                case "8":
                    System.out.println("peace out incoming receipts!");
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
