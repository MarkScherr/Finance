package home.finance.mapper;

import home.finance.entity.dao.Bill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillRowMapper implements RowMapper<Bill> {

    @Override
    public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getInt("id"));
        bill.setBillName(rs.getString("bill_name"));
        bill.setBillType(rs.getString("bill_type"));
        bill.setMinimumPaymentAmount(rs.getInt("min_payment_amount"));
        bill.setMaximumPaymentAmount(rs.getInt("max_payment_amount"));
        bill.setPaymentDate(rs.getInt("payment_date"));
        bill.setTotalDue(rs.getInt("total_due"));
        bill.setAutoPay(rs.getBoolean("is_auto_pay"));
        return bill;
    }
}
