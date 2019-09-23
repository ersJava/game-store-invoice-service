package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {

    private static final String SELECT_FEE_BY_PRODUCT_TYPE_SQL =
            "select * from processing_fee where product_type = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProcessingFee getProcessingFee(String itemType) {
        return jdbcTemplate.queryForObject(SELECT_FEE_BY_PRODUCT_TYPE_SQL, this::mapToRowFee, itemType);
    }

    private ProcessingFee mapToRowFee(ResultSet rs, int rowNum) throws SQLException {

        ProcessingFee pf = new ProcessingFee();
        pf.setProductType(rs.getString("product_type"));
        pf.setFee(rs.getBigDecimal("fee"));

        return pf;
    }
}
