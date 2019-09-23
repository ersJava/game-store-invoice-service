package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoJdbcTemplateImpl implements GameDao {

    private static final String INSERT_GAME_SQL =
            "insert into game (title, esrb_rating, description, price, studio, quantity) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_GAME_SQL =
            "select * from game where game_id = ?";

    private static final String SELECT_ALL_GAMES_SQL =
            "select * from game";

    private static final String DELETE_GAME_SQL =
            "delete from game where game_id = ?";

    private static final String UPDATE_GAME_SQL =
            "update game set title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity =? where game_id = ?";

    private static final String SELECT_GAMES_BY_STUDIO_SQL =
            "select * from game where studio = ?";

    private static final String SELECT_GAMES_BY_ESRBRating_SQL =
            "select * from game where esrb_rating = ?";

    private static final String SELECT_GAME_BY_TITLE =
            "select * from game where title = ?";

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void GameDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private Games mapToRowGame(ResultSet rs, int rowNum) throws SQLException {

        Games game = new Games();
        game.setGameId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setESRBRating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));

        return game;
    }

    @Override
    @Transactional
    public Games addGame(Games game) {

        jdbcTemplate.update(INSERT_GAME_SQL,
                game.getTitle(),
                game.getESRBRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        game.setGameId(id);

        return game;
    }

    @Override
    public Games getGame(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapToRowGame, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Games> getAllGames() {

        return jdbcTemplate.query(SELECT_ALL_GAMES_SQL, this::mapToRowGame);
    }

    @Override
    public void updateGame(Games game) {

        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getESRBRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getGameId());
    }

    @Override
    public void deleteGame(int id) {

        jdbcTemplate.update(DELETE_GAME_SQL, id);
    }

    @Override
    public List<Games> getGamesByStudio(String studio) {
        return jdbcTemplate.query(SELECT_GAMES_BY_STUDIO_SQL, this::mapToRowGame, studio);
    }

    @Override
    public List<Games> getGamesByESRBRating(String rating) {
        return jdbcTemplate.query(SELECT_GAMES_BY_ESRBRating_SQL, this::mapToRowGame, rating);
    }

    @Override
    public Games getGameByTitle(String title) {
        return jdbcTemplate.queryForObject(SELECT_GAME_BY_TITLE, this::mapToRowGame, title);
    }
}
