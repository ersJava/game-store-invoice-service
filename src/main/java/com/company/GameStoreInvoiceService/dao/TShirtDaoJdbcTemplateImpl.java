package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.TShirts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao {

    private static final String INSERT_TSHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";

    private static final String SELECT_TSHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_ALL_TSHIRTS_SQL =
            "select * from t_shirt";

    private static final String DELETE_TSHIRT_SQL =
            "delete from t_shirt where t_shirt_id = ?";

    private static final String UPDATE_TSHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";

    private static final String SELECT_TSHIRT_BY_COLOR_SQL =
            "select * from t_shirt where color = ?";

    private static final String SELECT_TSHIRT_BY_SIZE_SQL =
            "select * from t_shirt where size = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public TShirts mapToRowTShirt(ResultSet rs, int numRow) throws SQLException {
        TShirts tShirts = new TShirts();
        tShirts.setSize(rs.getString("size"));
        tShirts.setColor(rs.getString("color"));
        tShirts.setDescription(rs.getString("description"));
        tShirts.setPrice(rs.getBigDecimal("price"));
        tShirts.setQuantity(rs.getInt("quantity"));
        tShirts.setTShirtId(rs.getInt("t_shirt_id"));

        return tShirts;
    }

    @Override
    @Transactional
    public TShirts addTShirt(TShirts tShirts) {

        jdbcTemplate.update(INSERT_TSHIRT_SQL,
                tShirts.getSize(),
                tShirts.getColor(),
                tShirts.getDescription(),
                tShirts.getPrice(),
                tShirts.getQuantity());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        tShirts.setTShirtId(id);

        return tShirts;
    }

    @Override
    public TShirts getTShirt(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapToRowTShirt, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<TShirts> getAllTShirts() {
        return jdbcTemplate.query(SELECT_ALL_TSHIRTS_SQL, this::mapToRowTShirt);
    }

    @Override
    public void updateTShirt(TShirts tShirts) {
        jdbcTemplate.update(UPDATE_TSHIRT_SQL,
                tShirts.getSize(),
                tShirts.getColor(),
                tShirts.getDescription(),
                tShirts.getPrice(),
                tShirts.getQuantity(),
                tShirts.getTShirtId());
    }

    @Override
    public void deleteTShirt(int id) {

        jdbcTemplate.update(DELETE_TSHIRT_SQL, id);
    }

    @Override
    public List<TShirts> getTShirtsByColor(String color) {
        return jdbcTemplate.query(SELECT_TSHIRT_BY_COLOR_SQL, this::mapToRowTShirt, color);
    }

    @Override
    public List<TShirts> getTShirtsBySize(String size) {
        return jdbcTemplate.query(SELECT_TSHIRT_BY_SIZE_SQL, this::mapToRowTShirt, size);
    }
}
