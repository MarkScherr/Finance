package home.finance.service;

import home.finance.constants.CurrentTotalConstants;
import home.finance.entity.dao.Bill;
import home.finance.entity.dao.CurrentTotals;
import home.finance.entity.dao.Income;
import home.finance.repository.BillRepository;
import home.finance.repository.CurrentTotalsRepository;
import home.finance.repository.IncomeRepository;
import home.finance.repository.ReceiptRepository;
import home.finance.util.SpeakerUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BillPayCalculatorService {
    private final BillRepository billRepository;
    private final IncomeRepository incomeRepository;
    private final ReceiptRepository receiptRepository;
    private final CurrentTotalsRepository currentTotalsRepository;

    public BillPayCalculatorService(BillRepository billRepository, IncomeRepository incomeRepository,
                                    ReceiptRepository receiptRepository, CurrentTotalsRepository currentTotalsRepository) {
        this.billRepository = billRepository;
        this.incomeRepository = incomeRepository;
        this.receiptRepository = receiptRepository;
        this.currentTotalsRepository = currentTotalsRepository;
    }

    public void determineMonthlyPayments(Scanner scanner) {
        // "Food and Gas"
        List<Bill> bills = billRepository.findAllUnpaidBills();
        List<CurrentTotals> currentTotals = currentTotalsRepository.findAll();
        int savings = 0;
        int checking = 0;
        for( CurrentTotals currentTotal : currentTotals) {
            if (currentTotal.getType().equals(CurrentTotalConstants.CHECKING_TYPE)) {
                checking = currentTotal.getAmount();
            } else {
                savings = currentTotal.getAmount();
            }
        }
        bills.sort(Comparator.comparingInt(Bill::getPaymentDate));
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        List<Income> income = incomeRepository.findAllNewIncome(month, year);
        List<Bill> firstCheckBills = new ArrayList<>();
        List<Bill> secondCheckBills = new ArrayList<>();
        Income firstCheck = income.get(0);

        Income secondCheck = income.get(1);
        LocalDate firstCheckDate = firstCheck.getDate();
        LocalDate secondCheckDate = secondCheck.getDate();
        bills.sort((b1, b2) -> Integer.compare(b2.getTotalDue(), b1.getTotalDue()));
        for(Bill bill : bills) {
            int perCheckAmount = bill.getMinimumPaymentAmount() / 2;
            bill.setMinimumPaymentAmount(perCheckAmount);
            firstCheckBills.add(bill);
            secondCheckBills.add(bill);
        }
        System.out.println();


    }
}
