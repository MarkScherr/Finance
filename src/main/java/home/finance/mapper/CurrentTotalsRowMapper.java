package home.finance.mapper;

import home.finance.entity.dao.CurrentTotals;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentTotalsRowMapper implements RowMapper<CurrentTotals> {

    @Override
    public CurrentTotals mapRow(ResultSet rs, int rowNum) throws SQLException {
        CurrentTotals currentTotals = new CurrentTotals();
        currentTotals.setId(rs.getString("id"));
        currentTotals.setType(rs.getString("type"));
        currentTotals.setAmount(rs.getInt("amount"));
        return currentTotals;
    }
}
