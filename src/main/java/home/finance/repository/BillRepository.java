package home.finance.repository;

import home.finance.entity.dao.Bill;
import home.finance.mapper.BillRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class BillRepository {

    private final JdbcTemplate jdbcTemplate;

    public BillRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bill> findAll() {
        String sql = "SELECT * FROM \"Finance\".\"Bill\"";
        return jdbcTemplate.query(sql, new BillRowMapper());
    }

    public void addBill(Bill bill) {
        String sql = "INSERT INTO \"Finance\".\"Bill\" (" +
                "bill_name, bill_type, min_payment_amount, payment_date, max_payment_amount, " +
                "total_due, is_auto_pay) VALUES(?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                bill.getBillName(), bill.getBillType(), bill.getMinimumPaymentAmount(),
                bill.getPaymentDate(), bill.getMaximumPaymentAmount(), bill.getTotalDue(),
                bill.isAutoPay()
        );
    }

    public int updateBill(Bill bill) {
        String sql = "UPDATE \"Finance\".\"Bill\" SET bill_name = ?, bill_type = ?, " +
                "min_payment_amount = ?, max_payment_amount = ?, " +
                "total_due = ?, is_auto_pay = ? WHERE id = ?;";
        return jdbcTemplate.update(sql,
                bill.getBillName(), bill.getBillType(), bill.getMinimumPaymentAmount(),
                bill.getPaymentDate(), bill.getMaximumPaymentAmount(), bill.getTotalDue(),
                bill.isAutoPay()
        );
    }

    public void removeBill(int id) {
        String sql = "DELETE FROM \"Finance\".\"Bill\" WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }
}
