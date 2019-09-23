package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao {

    private static final String SELECT_SALESTAXRATE_SQL =
            "select * from sales_tax_rate where state = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalesTaxRate getSalesTaxRate(String state) {
        return jdbcTemplate.queryForObject(SELECT_SALESTAXRATE_SQL, this::mapToRowSTR, state);
    }

        private SalesTaxRate mapToRowSTR(ResultSet rs, int rowNum) throws SQLException {

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));

        return salesTaxRate;

    }
}
