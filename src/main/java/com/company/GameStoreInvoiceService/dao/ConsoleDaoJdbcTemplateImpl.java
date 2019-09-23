package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.Consoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao {

    private static final String INSERT_CONSOLE_SQL =
            "insert into console (model, manufacturer, memory_amount, processor, price, quantity) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_CONSOLE_SQL =
            "select * from console where console_id = ?";

    private static final String SELECT_ALL_CONSOLES_SQL =
            "select * from console";

    private static final String DELETE_CONSOLE_SQL =
            "delete from console where console_id = ?";

    private static final String UPDATE_CONSOLE_SQL =
            "update console set model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ? where console_id = ?";

    private static final String SELECT_CONSOLES_BY_MANUFACTURER_SQL =
            "select * from console where manufacturer = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    private Consoles mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Consoles consoles = new Consoles();
        consoles.setConsoleId(rs.getInt("console_id"));
        consoles.setModel(rs.getString("model"));
        consoles.setManufacturer(rs.getString("manufacturer"));
        consoles.setMemoryAmount(rs.getString("memory_amount"));
        consoles.setProcessor(rs.getString("processor"));
        consoles.setPrice(rs.getBigDecimal("price"));
        consoles.setQuantity(rs.getInt("quantity"));
        return consoles;
    }


    @Override
    @Transactional
    public Consoles addConsole(Consoles consoles) {

        jdbcTemplate.update(INSERT_CONSOLE_SQL,
                consoles.getModel(),
                consoles.getManufacturer(),
                consoles.getMemoryAmount(),
                consoles.getProcessor(),
                consoles.getPrice(),
                consoles.getQuantity());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        consoles.setConsoleId(id);
        return consoles;
    }

    @Override
    public Consoles getConsole(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapRowToConsole, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Consoles> getAllConsoles() {
        return jdbcTemplate.query(SELECT_ALL_CONSOLES_SQL, this::mapRowToConsole);
    }

    @Override
    public void updateConsole(Consoles consoles) {

        jdbcTemplate.update(UPDATE_CONSOLE_SQL,
                consoles.getModel(),
                consoles.getManufacturer(),
                consoles.getMemoryAmount(),
                consoles.getProcessor(),
                consoles.getPrice(),
                consoles.getQuantity(),
                consoles.getConsoleId());
    }

    @Override
    public void deleteConsole(int id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);
    }

    @Override
    public List<Consoles> getConsolesByManufacturer(String manufacturer) {
        return jdbcTemplate.query(SELECT_CONSOLES_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
    }
}
