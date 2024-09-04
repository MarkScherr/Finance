package home.finance.mapper;

import home.finance.entity.dao.Receipt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptRowMapper implements RowMapper<Receipt> {

    @Override
    public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt("id"));
        receipt.setName(rs.getString("name"));
        receipt.setType(rs.getString("type"));
        receipt.setAmount(rs.getInt("amount"));
        receipt.setDate(rs.getDate("date").toLocalDate());
        receipt.setRecurring(rs.getBoolean("is_recurring"));
        receipt.setId(rs.getInt("id"));
        return receipt;
    }
}
