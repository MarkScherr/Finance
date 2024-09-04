package home.finance.service;

import home.finance.entity.dao.Receipt;
import home.finance.repository.ReceiptRepository;
import home.finance.util.SpeakerUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReceiptService {


    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<Receipt> findAllReceiptInDateRange(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        return receiptRepository.findAll(sinceDate);
    }

    public void addReceipt(Scanner scanner) {
        Receipt receipt = new Receipt();
        System.out.println();
        setNameFromInput(scanner, receipt);
        setTypeFromInput(scanner, receipt);
        setAmountFromInput(scanner, receipt);
        setDateFromInput(scanner, receipt);
        setRecurringFromInput(scanner, receipt);
        receiptRepository.addReceipt(receipt);

    }

    public void updateReceipt(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Receipt> receipts = receiptRepository.findAll(sinceDate);
        int selection = getReceiptSelection(receipts, scanner);
        Receipt receiptToUpdate = receipts.get(selection);
        System.out.println("Which field would you like to update:");
        System.out.println(
                "1. receipt name\n" +
                "2. receipt type \n" +
                "3. amount\n" +
                "4. date\n" +
                "5. is recurring\n" +
                "10. exit\n"
        );
        selection = SpeakerUtil.handleInputInteger(scanner,
                "Please enter selection: ",
                false);

        switch (selection) {
            case 1:
                setNameFromInput(scanner, receiptToUpdate);
                break;
            case 2:
                setTypeFromInput(scanner, receiptToUpdate);
                break;
            case 3:
                setAmountFromInput(scanner, receiptToUpdate);
                break;
            case 4:
                setDateFromInput(scanner, receiptToUpdate);
                break;
            case 5:
                setRecurringFromInput(scanner, receiptToUpdate);
                break;
            case 10:
                System.out.println("\nSmell ya later!");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
        receiptRepository.updateReceipt(receiptToUpdate);
    }

    private int getReceiptSelection(List<Receipt> receipts, Scanner scanner) {
        System.out.println();
        for (int i = 0; i < receipts.size(); i++) {
            System.out.println(i + ". " + receipts.get(i).getDate() + " : " +
                    receipts.get(i).getName() + " : " + receipts.get(i).getAmount());
        }

        return SpeakerUtil.handleInputInteger(scanner,
                "Please enter selection: ",
                false);
    }

    public void displayReceipt(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Receipt> receipts = receiptRepository.findAll(sinceDate);
        if(receipts.isEmpty()) {
            System.out.println("There are no receipts in this date range");
        } else {
            int selection = getReceiptSelection(receipts, scanner);
            Receipt receiptToDisplay = receipts.get(selection);
            System.out.println(
                    "\nreceipt name: " + receiptToDisplay.getName() + "\n" +
                            "receipt type: " + receiptToDisplay.getType() + "\n" +
                            "amount: " + receiptToDisplay.getAmount() + "\n" +
                            "date: " + receiptToDisplay.getDate() + "\n" +
                            "is recurring: " + receiptToDisplay.isRecurring()
            );

        }
    }

    public void removeReceipt(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Receipt> receipts = receiptRepository.findAll(sinceDate);
        if (receipts.isEmpty()) {
            System.out.println("Unable to find any receipt in that time period");
        } else {
            int selection = getReceiptSelection(receipts, scanner);
            receiptRepository.removeReceipt(receipts.get(selection).getId());
        }
    }

    private void setNameFromInput(Scanner scanner, Receipt receipt) {
        receipt.setName(
            SpeakerUtil.handleInputString(scanner,
                    "Please enter RECEIPT NAME: ")
        );
    }

    private void setTypeFromInput(Scanner scanner, Receipt receipt) {
        receipt.setType(
            SpeakerUtil.handleInputString(scanner,
                    "Please enter RECEIPT TYPE: ")
        );
    }

    private void setAmountFromInput(Scanner scanner, Receipt receipt) {
        receipt.setAmount (
            SpeakerUtil.handleInputInteger(scanner,
                    "Please enter RECEIPT AMOUNT: ",
                    false
            )
        );
    }

    private void setDateFromInput(Scanner scanner, Receipt receipt) {
        receipt.setDate(
            SpeakerUtil.handleInputDate(scanner,
                    "Please enter RECEIPT DATE (YYYY/MM/DD) or type 't' for today: "
            )
        );
    }

    private void setRecurringFromInput(Scanner scanner, Receipt receipt) {
        receipt.setRecurring(
                SpeakerUtil.handleInputBooleanString(scanner,
                        "Is receipt RECEIPT RECURRING (y/n): "
                )
        );
    }
}
