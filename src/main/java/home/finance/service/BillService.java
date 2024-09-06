package home.finance.service;

import home.finance.entity.dao.Bill;
import home.finance.repository.BillRepository;
import home.finance.util.SpeakerUtil;

import java.util.List;
import java.util.Scanner;

public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> findAllBills() {
        return billRepository.findAll();
    }

    public void findAllUnpaidBills() {
        List<Bill> unpaidBills = billRepository.findAllUnpaidBills();
        for (Bill bill : unpaidBills) {
            System.out.println(bill.getPaymentDate() + " : " + bill.getBillName() + " : " +
                    bill.getMinimumPaymentAmount());
        }
    }

    public void addBill() {
        Scanner scanner = new Scanner(System.in);
        Bill bill = new Bill();
        System.out.println();
        setBillNameFromInput(scanner, bill);
        setBillTypeFromInput(scanner, bill);
        setMinPaymentFromInput(scanner, bill);
        setMaxPaymentFromInput(scanner, bill);
        setDueDateFromInput(scanner, bill);
        setTotalDueFromInput(scanner, bill);
        setAutoPayFromInput(scanner, bill);
        setPaidFromInput(scanner, bill);

        billRepository.addBill(bill);

    }

    public void updateBill() {
        Scanner scanner = new Scanner(System.in);
        List<Bill> bills = billRepository.findAll();
        int selection = getBillSelection(bills, scanner);
        Bill billToUpdate = bills.get(selection);
        System.out.println("Which field would you like to update:");
        System.out.println(
                "1. bill name\n" +
                "2. bill type \n" +
                "3. minimum payment\n" +
                "4. maximum payment\n" +
                "5. date due\n" +
                "6. total due\n" +
                "7. is auto pay\n" +
                "8. is bill paid\n" +
                "20. exit application"
        );
        selection = SpeakerUtil.handleInputInteger(scanner,
                "Please enter selection: ",
                false);

        switch (selection) {
            case 1:
                setBillNameFromInput(scanner, billToUpdate);
                break;
            case 2:
                setBillTypeFromInput(scanner, billToUpdate);
                break;
            case 3:
                setMinPaymentFromInput(scanner, billToUpdate);
                break;
            case 4:
                setMaxPaymentFromInput(scanner, billToUpdate);
                break;
            case 5:
                setDueDateFromInput(scanner, billToUpdate);
                break;
            case 6:
                setTotalDueFromInput(scanner, billToUpdate);
                break;
            case 7:
                setAutoPayFromInput(scanner, billToUpdate);
                break;
            case 8:
                setPaidFromInput(scanner, billToUpdate);
                break;
            case 20:
                System.out.println("\nSmell ya later!");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
        billRepository.updateBill(billToUpdate);
    }

    private int getBillSelection(List<Bill> bills, Scanner scanner) {
        for (int i = 0; i < bills.size(); i++) {
            System.out.println(i + ". " + bills.get(i).getPaymentDate() + " : " +
                    bills.get(i).getBillName() + " : " + bills.get(i).getMinimumPaymentAmount());
        }
        return SpeakerUtil.handleInputInteger(scanner,
                "Please enter selection: ",
                false);
    }

    public void displayBill(Scanner scanner) {
        List<Bill> bills = billRepository.findAll();
        int selection = getBillSelection(bills, scanner);
        Bill billToDisplay = bills.get(selection);
        System.out.println(
                "\nbill name: " + billToDisplay.getBillName() + "\n" +
                "bill type: " + billToDisplay.getBillType() + "\n" +
                "minimum payment: " + billToDisplay.getMinimumPaymentAmount() + "\n" +
                "maximum payment: " + billToDisplay.getMaximumPaymentAmount() + "\n" +
                "date due each month: " + billToDisplay.getPaymentDate() + "\n" +
                "total due: " + billToDisplay.getTotalDue() + "\n" +
                "is auto pay: " + billToDisplay.isAutoPay()
        );
    }

    public void removeBill(Scanner scanner) {
        List<Bill> bills = billRepository.findAll();
        int selection = getBillSelection(bills, scanner);
        billRepository.removeBill(bills.get(selection).getId());
    }

    private void setBillNameFromInput(Scanner scanner, Bill bill) {
        bill.setBillName(
            SpeakerUtil.handleInputString(scanner,
                    "Please enter BILL NAME: ")
        );
    }

    private void setBillTypeFromInput(Scanner scanner, Bill bill) {
        bill.setBillType(
            SpeakerUtil.handleInputString(scanner,
                    "Please enter BILL TYPE: ")
        );
    }

    private void setMinPaymentFromInput(Scanner scanner, Bill bill) {
        bill.setMinimumPaymentAmount(
            SpeakerUtil.handleInputInteger(scanner,
                    "Please enter MINIMUM PAYMENT AMOUNT: ",
                    false
            )
        );
    }

    private void setMaxPaymentFromInput(Scanner scanner, Bill bill) {
        bill.setMaximumPaymentAmount(
            SpeakerUtil.handleInputInteger(scanner,
                    "Please enter MAXIMUM PAYMENT AMOUNT (or -1 if none): ",
                    true
            )
        );
    }

    private void setDueDateFromInput(Scanner scanner, Bill bill) {
        bill.setPaymentDate(
            SpeakerUtil.handleInputInteger(scanner,
                    "Please enter PAYMENT DATE ie. 15: ",
                    false
            )
        );
    }

    private void setTotalDueFromInput(Scanner scanner, Bill bill) {
        bill.setTotalDue(
            SpeakerUtil.handleInputInteger(scanner,
                    "Please enter TOTAL DUE (or -1 if recurring): ",
                    true
            )
        );
    }

    private void setAutoPayFromInput(Scanner scanner, Bill bill) {
        bill.setAutoPay(
            SpeakerUtil.handleInputBooleanString(scanner,
                    "Is bill AUTO PAY (y/n): "
            )
        );
    }

    private void setPaidFromInput(Scanner scanner, Bill bill) {
        bill.setPaid(
                SpeakerUtil.handleInputBooleanString(scanner,
                        "Is bill completely PAID (y/n): "
                )
        );
    }
}
