package home.finance.repository;

import home.finance.entity.dao.CurrentTotals;
import home.finance.mapper.CurrentTotalsRowMapper;
import home.finance.mapper.CurrentTotalsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class CurrentTotalsRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public CurrentTotalsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CurrentTotals> findAll() {
        String sql = "SELECT * FROM \"Finance\".\"CurrentTotals\"";
        return jdbcTemplate.query(sql, new CurrentTotalsRowMapper());
    }

    public void addCurrentTotals(CurrentTotals currentTotals) {
        String sql = "INSERT INTO \"Finance\".\"CurrentTotals\" (" +
                "name, type, amount)  VALUES(?, ?, ?);";
        jdbcTemplate.update(sql,
                currentTotals.getId(), currentTotals.getType(), currentTotals.getAmount());
    }

    public void updateCurrentTotals(CurrentTotals currentTotals) {
        String sql = "UPDATE \"Finance\".\"CurrentTotals\" SET id = ?, type = ?, " +
                "amount = ? WHERE id = ?;";
        jdbcTemplate.update(sql,
                currentTotals.getId(), currentTotals.getType(), currentTotals.getAmount());
    }

    public void removeCurrentTotals(String id) {
        String sql = "DELETE FROM \"Finance\".\"CurrentTotals\" WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }
}
