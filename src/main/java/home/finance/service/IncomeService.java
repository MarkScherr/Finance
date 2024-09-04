package home.finance.service;

import home.finance.entity.dao.Income;
import home.finance.repository.IncomeRepository;
import home.finance.util.SpeakerUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class IncomeService {


    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<Income> findAllIncomeInDateRange(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        return incomeRepository.findAll(sinceDate);
    }

    public void addIncome(Scanner scanner) {
        Income income = new Income();
        System.out.println();
        setNameFromInput(scanner, income);
        setTypeFromInput(scanner, income);
        setAmountFromInput(scanner, income);
        setDateFromInput(scanner, income);
        setRecurringFromInput(scanner, income);
        setPayFrequencyFromInput(scanner, income);
        addOneYearOfIncome(income);
    }

    private void addOneYearOfIncome(Income income) {
        String paymentFrequency = income.getPaymentFrequency();
        if(paymentFrequency.equalsIgnoreCase("BW")) {
            LocalDate date = income.getDate();
            incomeRepository.addIncome(income);
            for (int i = 0; i < 26; i++) {
                date = date.plusWeeks(2);
                income.setDate(date);
                incomeRepository.addIncome(income);
            }
        } else if (paymentFrequency.equalsIgnoreCase("BM")) {
           //TODO
        } else if (paymentFrequency.equalsIgnoreCase("W")) {
            LocalDate date = income.getDate();
            incomeRepository.addIncome(income);
            for (int i = 0; i < 52; i++) {
                date = date.plusWeeks(1);
                income.setDate(date);
                incomeRepository.addIncome(income);
            }
        }
    }

    public void updateIncome(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Income> incomes = incomeRepository.findAll(sinceDate);
        int selection = getIncomeSelection(incomes, scanner);
        Income incomeToUpdate = incomes.get(selection);
        System.out.println("Which field would you like to update:");
        System.out.println(
                "1. income name\n" +
                "2. income type \n" +
                "3. amount\n" +
                "4. date\n" +
                "5. is recurring\n" +
                "6. pay frequency\n" +
                "10. exit\n"
        );
        selection = SpeakerUtil.handleInputInteger(scanner,
                "Please enter selection: ",
                false);

        switch (selection) {
            case 1:
                setNameFromInput(scanner, incomeToUpdate);
                break;
            case 2:
                setTypeFromInput(scanner, incomeToUpdate);
                break;
            case 3:
                setAmountFromInput(scanner, incomeToUpdate);
                break;
            case 4:
                setDateFromInput(scanner, incomeToUpdate);
                break;
            case 5:
                setRecurringFromInput(scanner, incomeToUpdate);
                break;
            case 6:
                setPayFrequencyFromInput(scanner, incomeToUpdate);
                break;
            case 10:
                System.out.println("\nSmell ya later!");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
        incomeRepository.updateIncome(incomeToUpdate);
    }

    private int getIncomeSelection(List<Income> incomes, Scanner scanner) {
        System.out.println();
        for (int i = 0; i < incomes.size(); i++) {
            System.out.println(i + ". " + incomes.get(i).getDate() + ":" +
                    incomes.get(i).getName() + ":" + incomes.get(i).getAmount());
        }

        return SpeakerUtil.handleInputInteger(scanner,
                "Please enter selection: ",
                false);
    }

    public void displayIncome(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Income> incomes = incomeRepository.findAll(sinceDate);
        int selection = getIncomeSelection(incomes, scanner);
        Income incomeToDisplay = incomes.get(selection);
        System.out.println(
                "\nincome name: " + incomeToDisplay.getName() + "\n" +
                "income type: " + incomeToDisplay.getType() + "\n" +
                "amount: " + incomeToDisplay.getAmount() + "\n" +
                "date: " + incomeToDisplay.getDate() + "\n" +
                "is recurring: " + incomeToDisplay.isRecurring()
        );
    }

    public void removeIncome(Scanner scanner) {
        LocalDate sinceDate = SpeakerUtil.handleInputMonths(scanner);
        List<Income> incomes = incomeRepository.findAll(sinceDate);
        if (incomes.isEmpty()) {
            System.out.println("Unable to find any income in that time period");
        } else {
            int selection = getIncomeSelection(incomes, scanner);
            incomeRepository.removeIncome(incomes.get(selection).getId());
        }
    }

    private void setNameFromInput(Scanner scanner, Income income) {
        income.setName(
            SpeakerUtil.handleInputString(scanner,
                    "Please enter INCOME NAME: ")
        );
    }

    private void setTypeFromInput(Scanner scanner, Income income) {
        income.setType(
            SpeakerUtil.handleInputString(scanner,
                    "Please enter INCOME TYPE: ")
        );
    }

    private void setAmountFromInput(Scanner scanner, Income income) {
        income.setAmount (
            SpeakerUtil.handleInputInteger(scanner,
                    "Please enter INCOME AMOUNT: ",
                    false
            )
        );
    }

    private void setDateFromInput(Scanner scanner, Income income) {
        income.setDate(
            SpeakerUtil.handleInputDate(scanner,
                    "Please enter INCOME DATE (YYYY/MM/DD) or type 't' for today: "
            )
        );
    }

    private void setRecurringFromInput(Scanner scanner, Income income) {
        income.setRecurring(
                SpeakerUtil.handleInputBooleanString(scanner,
                        "Is income INCOME RECURRING (y/n): "
                )
        );
    }

    private void setPayFrequencyFromInput(Scanner scanner, Income income) {
        income.setPaymentFrequency(
                SpeakerUtil.handleInputString(scanner,
                        "Please enter PAYMENT FREQUENCY (BW, BM, W): ")
        );

    }
}
