package home.finance.mapper;

import home.finance.entity.dao.Income;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeRowMapper implements RowMapper<Income> {

    @Override
    public Income mapRow(ResultSet rs, int rowNum) throws SQLException {
        Income income = new Income();
        income.setId(rs.getInt("id"));
        income.setName(rs.getString("name"));
        income.setType(rs.getString("type"));
        income.setAmount(rs.getInt("amount"));
        income.setDate(rs.getDate("date").toLocalDate());
        income.setRecurring(rs.getBoolean("is_recurring"));
        income.setPaymentFrequency(rs.getString("payment_frequency"));
        return income;
    }
}
