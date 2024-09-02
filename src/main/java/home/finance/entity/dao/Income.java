package home.finance.entity.dao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Income {
    private int id;
    private String name;
    private String type;
    private int amount;
    private LocalDate date = setDate();
    private boolean isRecurring;

    public LocalDate setDate() {
        return LocalDate.now();
    }
}
