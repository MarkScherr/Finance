package home.finance.repository;

import home.finance.entity.dao.Income;
import home.finance.mapper.IncomeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class IncomeRepository {

    private final JdbcTemplate jdbcTemplate;

    public IncomeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Income> findAll(LocalDate sinceDate) {
        String sql = "SELECT * FROM \"Finance\".\"Income\" WHERE date > ?";
        return jdbcTemplate.query(sql, new IncomeRowMapper(), sinceDate);
    }

    public void addIncome(Income income) {
        String sql = "INSERT INTO \"Finance\".\"Income\" (" +
                "name, type, amount, date, is_recurring, payment_frequency)  VALUES(?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                income.getName(), income.getType(), income.getAmount(),
                income.getDate(), income.isRecurring(), income.getPaymentFrequency());
    }

    public void updateIncome(Income income) {
        String sql = "UPDATE \"Finance\".\"Income\" SET name = ?, type = ?, " +
                "amount = ?, date = ?, is_recurring = ?, payment_frequency = ? WHERE id = ?;";
        jdbcTemplate.update(sql,
                income.getName(), income.getType(), income.getAmount(), income.getDate(),
                income.isRecurring(), income.getPaymentFrequency(), income.getId());
    }

    public void removeIncome(int id) {
        String sql = "DELETE FROM \"Finance\".\"Income\" WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }
}
