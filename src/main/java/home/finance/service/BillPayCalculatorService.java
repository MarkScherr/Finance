package home.finance.service;

import home.finance.entity.dao.Bill;
import home.finance.entity.dao.Income;
import home.finance.repository.BillRepository;
import home.finance.repository.CurrentTotalsRepository;
import home.finance.repository.IncomeRepository;
import home.finance.repository.ReceiptRepository;
import home.finance.util.SpeakerUtil;

import java.time.LocalDate;
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
        List<Bill> bills = billRepository.findAll();
        bills.sort(Comparator.comparingInt(Bill::getPaymentDate));
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Income> income = incomeRepository.findAll(sinceDate);


    }
}
