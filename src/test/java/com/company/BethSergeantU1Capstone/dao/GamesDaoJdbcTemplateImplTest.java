package com.company.BethSergeantU1Capstone.dao;

import com.company.GameStoreInvoiceService.dao.GameDao;
import com.company.GameStoreInvoiceService.model.Games;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GamesDaoJdbcTemplateImplTest {

    @Autowired
    protected GameDao gameDao;

    @Before
    public void setUp() throws Exception {

        List<Games> gameList = gameDao.getAllGames();
        gameList.stream().forEach(game -> gameDao.deleteGame(game.getGameId()));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    void addGetDeleteGame() {

        Games game = new Games();
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = gameDao.addGame(game);

        Games game2 = gameDao.getGame(game.getGameId());
        assertEquals(game, game2);

        gameDao.deleteGame(game.getGameId());
        game2 = gameDao.getGame(game.getGameId());
        assertNull(game2);
    }

    @Test
    void getAllGames() {

        Games game = new Games();
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = gameDao.addGame(game);

        game = new Games();
        game.setTitle("Chrono Trigger");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG that takes place in a fictitious alternate timeline of Earth where the characters travel through different time period from 65,000,000 B.C. at the dawn of civilization to 2300 A.D., a post-apocalyptic time period.");
        game.setPrice(new BigDecimal("7.99"));
        game.setStudio("Square Enix");
        game.setQuantity(21);
        game = gameDao.addGame(game);

        List<Games> gameList = gameDao.getAllGames();
        assertEquals(gameList.size(), 2);
    }

    @Test
    public void updateGame() {

        Games game = new Games();
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = gameDao.addGame(game);

        game.setTitle("FF4");
        game.setESRBRating("EVERYONE 10+");
        game.setDescription("UPDATE");
        game.setPrice(new BigDecimal("1.99"));
        game.setStudio("SQUARE");
        game.setQuantity(1);
        gameDao.updateGame(game);

        Games game2 = gameDao.getGame(game.getGameId());
        assertEquals(game, game2);
    }

    @Test
    public void getGamesByStudio() {

        Games game = new Games();
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = gameDao.addGame(game);

        game = new Games();
        game.setTitle("Chrono Trigger");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG that takes place in a fictitious alternate timeline of Earth where the characters travel through different time period from 65,000,000 B.C. at the dawn of civilization to 2300 A.D., a post-apocalyptic time period.");
        game.setPrice(new BigDecimal("7.99"));
        game.setStudio("Square Enix");
        game.setQuantity(21);
        game = gameDao.addGame(game);

        List<Games> gameList = gameDao.getGamesByStudio("Square Enix");
        assertEquals(gameList.size(), 2);

        gameList = gameDao.getGamesByStudio("Rockstar");
        assertEquals(gameList.size(), 0);

    }

    @Test
    public void getGamesByESRBRating() {
        Games game = new Games();
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = gameDao.addGame(game);

        game = new Games();
        game.setTitle("Chrono Trigger");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG that takes place in a fictitious alternate timeline of Earth where the characters travel through different time period from 65,000,000 B.C. at the dawn of civilization to 2300 A.D., a post-apocalyptic time period.");
        game.setPrice(new BigDecimal("7.99"));
        game.setStudio("Square Enix");
        game.setQuantity(21);
        game = gameDao.addGame(game);

        List<Games> gameList = gameDao.getGamesByESRBRating("Everyone 10+");
        assertEquals(gameList.size(), 2);

        gameList = gameDao.getGamesByESRBRating("Teen");
        assertEquals(gameList.size(), 0);
    }

    @Test
    public void getGameByTitle() {
        Games game = new Games();
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = gameDao.addGame(game);

        Games game2 = gameDao.getGameByTitle("Final Fantasy VI");
        assertEquals(game, game2);
    }
}