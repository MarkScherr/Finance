package home.finance.repository;

import home.finance.entity.dao.Receipt;
import home.finance.mapper.ReceiptRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class ReceiptRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReceiptRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Receipt> findAll(LocalDate sinceDate) {
        String sql = "SELECT * FROM \"Finance\".\"Receipt\" WHERE date > ?";
        return jdbcTemplate.query(sql, new ReceiptRowMapper(), sinceDate);
    }

    public void addReceipt(Receipt receipt) {
        String sql = "INSERT INTO \"Finance\".\"Receipt\" (" +
                "name, type, amount, date, is_recurring)  VALUES(?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                receipt.getName(), receipt.getType(), receipt.getAmount(), receipt.getDate(), receipt.isRecurring());
    }

    public void updateReceipt(Receipt receipt) {
        String sql = "UPDATE \"Finance\".\"Receipt\" SET name = ?, type = ?, " +
                "amount = ?, date = ?, is_recurring = ? WHERE id = ?;";
        jdbcTemplate.update(sql,
                receipt.getName(), receipt.getType(), receipt.getAmount(), receipt.getDate(),
                receipt.isRecurring(), receipt.getId());
    }

    public void removeReceipt(int id) {
        String sql = "DELETE FROM \"Finance\".\"Receipt\" WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }
}
