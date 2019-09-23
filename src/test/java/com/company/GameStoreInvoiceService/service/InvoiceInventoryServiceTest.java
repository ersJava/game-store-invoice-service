package com.company.GameStoreInvoiceService.service;

import com.company.GameStoreInvoiceService.dao.*;
import com.company.GameStoreInvoiceService.model.*;
import com.company.GameStoreInvoiceService.viewmodel.ConsoleViewModel;
import com.company.GameStoreInvoiceService.viewmodel.GameViewModel;
import com.company.GameStoreInvoiceService.viewmodel.InvoiceViewModel;
import com.company.GameStoreInvoiceService.viewmodel.TShirtViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class InvoiceInventoryServiceTest {

    InvoiceInventoryService invoiceInventoryService;

    ConsoleDao consoleDao;
    GameDao gameDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;


    @Before
    public void setUp() throws Exception {

        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpTShirtDaoMock();
        setUpInvoiceDaoMock();

        invoiceInventoryService = new InvoiceInventoryService(consoleDao, gameDao, tShirtDao, invoiceDao, processingFeeDao, salesTaxRateDao);
    }

    private void setUpGameDaoMock() {

        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Games game = new Games();
        game.setGameId(12);
        game.setTitle("Final Fantasy VI");
        game.setESRBRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);

        Games game1 = new Games();
        game1.setTitle("Final Fantasy VI");
        game1.setESRBRating("Everyone 10+");
        game1.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game1.setPrice(new BigDecimal("8.99"));
        game1.setStudio("Square Enix");
        game1.setQuantity(18);

        // Second mock Game Object
        Games game2 = new Games();
        game2.setGameId(26);
        game2.setTitle("Chrono Trigger");
        game2.setESRBRating("Everyone 10+");
        game2.setDescription("RPG that takes place in a fictitious alternate timeline of Earth where the characters travel through different time period from 65,000,000 B.C. at the dawn of civilization to 2300 A.D., a post-apocalyptic time period. .");
        game2.setPrice(new BigDecimal("7.99"));
        game2.setStudio("Square Enix");
        game2.setQuantity(21);

        // Mock addGame(); return game when gameDao.add(game2) is called
        doReturn(game).when(gameDao).addGame(game1);

        // Mock getAllGames(); return List<Game>
        List<Games> gameList = new ArrayList<>();
        gameList.add(game);
        doReturn(gameList).when(gameDao).getAllGames();

        // Mock getGame() return game when gameDao.getGame(int id) is called
        doReturn(game).when(gameDao).getGame(12);

        // Mock data for update
        Games gameUpdate = new Games();
        gameUpdate.setGameId(11);
        gameUpdate.setTitle("Secret of Mana");
        gameUpdate.setESRBRating("Everyone 10+");
        gameUpdate.setDescription("RPG that place in a fictional world, during an unspecified period following a war between a civilization and gods concerning the use of mana to fuel the Mana Fortress, a flying warship. Using the power of the Mana Sword, a hero destroyed the fortress and returned peace to the world.");
        gameUpdate.setPrice(new BigDecimal("7.99"));
        gameUpdate.setStudio("Square");
        gameUpdate.setQuantity(10);

        // Mock updateGame();
        doNothing().when(gameDao).updateGame(gameUpdate);
        doReturn(gameUpdate).when(gameDao).getGame(11);

        // Mock data for delete
        Games gameDelete = new Games();
        gameDelete.setGameId(21);
        gameDelete.setTitle("The Legend of Zelda: A Link To The Past");
        gameDelete.setESRBRating("Everyone 10+");
        gameDelete.setDescription("Play as Link, a young man living with his uncle south of Hyrule Castle. Princess Zelda, a descendant of the seven sages, is held captive in the castle dungeon by Agahnim, a treacherous wizard who has set forth a chain of events to release his dark master.");
        gameDelete.setPrice(new BigDecimal("12.99"));
        gameDelete.setStudio("Nintendo");
        gameDelete.setQuantity(1);

        doNothing().when(gameDao).deleteGame(21);
        doReturn(null).when(gameDao).getGame(21);

        // *** CUSTOM METHODS ***
        // Mock getGamesByESRBRating() returns 2
        List<Games> gamesByRatingList = new ArrayList<>();
        gamesByRatingList.add(game2);
        gamesByRatingList.add(game);
         doReturn(gamesByRatingList).when(gameDao).getGamesByESRBRating("Everyone 10+");

        // Mock getGamesByStudio() returns 2
        List<Games> gamesByStudioList = new ArrayList<>();
        gamesByStudioList.add(game2);
        gamesByStudioList.add(game);
        doReturn(gamesByStudioList).when(gameDao).getGamesByStudio("Square Enix");

        // Mock getGameByTitle returns 1
        doReturn(game).when(gameDao).getGameByTitle("Final Fantasy VI");

    }

    @Test
    public void saveGame() {

        GameViewModel game = new GameViewModel();

        game.setTitle("Final Fantasy VI");
        game.setEsrbRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);

        game = invoiceInventoryService.saveGame(game);

        GameViewModel fromService = invoiceInventoryService.findGameById(game.getGameId());
        assertEquals(fromService, game);
    }

    @Test
    public void findAllGames() {

        List<GameViewModel> gameList = invoiceInventoryService.findAllGames();
        assertEquals(1, gameList.size());
    }

    @Test
    public void findGameById() {

        GameViewModel game = new GameViewModel();

        game.setTitle("Final Fantasy VI");
        game.setEsrbRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);

        game = invoiceInventoryService.saveGame(game);

        GameViewModel fromService = invoiceInventoryService.findGameById(12);
        assertEquals(game, fromService);

    }

    @Test
    public void findGamesByStudio() {

        GameViewModel game = new GameViewModel();
        game.setTitle("Final Fantasy VI");
        game.setEsrbRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);
        game = invoiceInventoryService.saveGame(game);

        GameViewModel game3 = new GameViewModel();
        game3.setTitle("Chrono Trigger");
        game3.setEsrbRating("Everyone 10+");
        game3.setDescription("RPG that takes place in a fictitious alternate timeline of Earth where the characters travel through different time period from 65,000,000 B.C. at the dawn of civilization to 2300 A.D., a post-apocalyptic time period. .");
        game3.setPrice(new BigDecimal("7.99"));
        game3.setStudio("Square Enix");
        game3.setQuantity(21);
        game3 = invoiceInventoryService.saveGame(game);

        List<GameViewModel> gamesByStudioList = invoiceInventoryService.findGamesByStudio("Square Enix");
        assertEquals(2, gamesByStudioList.size());

        gamesByStudioList = invoiceInventoryService.findGamesByStudio("Rockstar");
        assertEquals(gamesByStudioList.size(), 0);

    }

    @Test
    public void findGamesByRating() {

//        GameViewModel game = new GameViewModel();
//        game.setTitle("Final Fantasy VI");
//        game.setEsrbRating("Everyone 10+");
//        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
//        game.setPrice(new BigDecimal("8.99"));
//        game.setStudio("Square Enix");
//        game.setQuantity(18);
//        game = invoiceInventoryService.saveGame(game);
//
//        GameViewModel game3 = new GameViewModel();
//        game3.setTitle("Chrono Trigger");
//        game3.setEsrbRating("Everyone 10+");
//        game3.setDescription("RPG that takes place in a fictitious alternate timeline of Earth where the characters travel through different time period from 65,000,000 B.C. at the dawn of civilization to 2300 A.D., a post-apocalyptic time period. .");
//        game3.setPrice(new BigDecimal("7.99"));
//        game3.setStudio("Square Enix");
//        game3.setQuantity(21);
//        game3 = invoiceInventoryService.saveGame(game);

        List<GameViewModel> gamesByRatingList = invoiceInventoryService.findGamesByRating("Everyone 10+");
        assertEquals(2, gamesByRatingList.size());

        gamesByRatingList = invoiceInventoryService.findGamesByRating("Teen");
        assertEquals(gamesByRatingList.size(), 0);
    }

    @Test
    public void getGameByTitle() {

        GameViewModel game = new GameViewModel();

        game.setTitle("Final Fantasy VI");
        game.setEsrbRating("Everyone 10+");
        game.setDescription("RPG about a conflict between the Gestahlian Empire conquering the world and a rebel faction opposed to them known as the Returners, taking place in a fantasy steampunk-style world.");
        game.setPrice(new BigDecimal("8.99"));
        game.setStudio("Square Enix");
        game.setQuantity(18);

        game = invoiceInventoryService.saveGame(game);

        GameViewModel fromService = invoiceInventoryService.getGameByTitle("Final Fantasy VI");
        assertEquals(game, fromService);

    }

    @Test
    public void updateGame() {

        GameViewModel gvmUpdate = new GameViewModel();

        Games gameUpdate = new Games();
        gameUpdate.setGameId(11);
        gameUpdate.setTitle("Secret of Mana");
        gameUpdate.setESRBRating("Everyone 10+");
        gameUpdate.setDescription("RPG that place in a fictional world, during an unspecified period following a war between a civilization and gods concerning the use of mana to fuel the Mana Fortress, a flying warship. Using the power of the Mana Sword, a hero destroyed the fortress and returned peace to the world.");
        gameUpdate.setPrice(new BigDecimal("7.99"));
        gameUpdate.setStudio("Square");
        gameUpdate.setQuantity(10);

        gvmUpdate.setGameId(gameUpdate.getGameId());
        gvmUpdate.setTitle(gameUpdate.getTitle());
        gvmUpdate.setEsrbRating(gameUpdate.getESRBRating());
        gvmUpdate.setDescription(gameUpdate.getDescription());
        gvmUpdate.setPrice(gameUpdate.getPrice());
        gvmUpdate.setStudio(gameUpdate.getStudio());
        gvmUpdate.setQuantity(gameUpdate.getQuantity());

        invoiceInventoryService.updateGame(gvmUpdate);

        GameViewModel gvm2 = invoiceInventoryService.findGameById(11);

        Games gameAfterUpdate = new Games();
        gameAfterUpdate.setGameId(gvm2.getGameId());
        gameAfterUpdate.setTitle(gvm2.getTitle());
        gameAfterUpdate.setESRBRating(gvm2.getEsrbRating());
        gameAfterUpdate.setDescription(gvm2.getDescription());
        gameAfterUpdate.setPrice(gvm2.getPrice());
        gameAfterUpdate.setStudio(gvm2.getStudio());
        gameAfterUpdate.setQuantity(gvm2.getQuantity());

        assertEquals(gameAfterUpdate, gameUpdate);
    }

    @Test
    public void removeGame() {

        invoiceInventoryService.removeGame(21);

        GameViewModel gvm = invoiceInventoryService.findGameById(21);

        assertNull(gvm);
    }

    public void setUpConsoleDaoMock() {

        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Consoles consoles = new Consoles();
        consoles.setConsoleId(38);
        consoles.setModel("Playstation 4");
        consoles.setManufacturer("Sony");
        consoles.setMemoryAmount("8GB");
        consoles.setProcessor("Jaguar CPU");
        consoles.setPrice(new BigDecimal("286.99"));
        consoles.setQuantity(26);

        Consoles consoles1 = new Consoles();
        consoles1.setModel("Playstation 4");
        consoles1.setManufacturer("Sony");
        consoles1.setMemoryAmount("8GB");
        consoles1.setProcessor("Jaguar CPU");
        consoles1.setPrice(new BigDecimal("286.99"));
        consoles1.setQuantity(26);

        // Second mock Console Object
        Consoles consoles2 = new Consoles();
        consoles2.setConsoleId(3);
        consoles2.setModel("Super Nintendo");
        consoles2.setManufacturer("Nintendo");
        consoles2.setMemoryAmount("128Mbit");
        consoles2.setProcessor("Ricoh 5A22");
        consoles2.setPrice(new BigDecimal("101.12"));
        consoles2.setQuantity(12);

        // Mock addConsole();
        doReturn(consoles).when(consoleDao).addConsole(consoles1);

        // Mock getAllConsoles();
        List<Consoles> consolesList = new ArrayList<>();
        consolesList.add(consoles);
        consolesList.add(consoles2);
        doReturn(consolesList).when(consoleDao).getAllConsoles();

        // Mock getConsole()
        doReturn(consoles).when(consoleDao).getConsole(38);

        // Mock data for update();
        Consoles consolesUpdate = new Consoles();
        consolesUpdate.setConsoleId(17);
        consolesUpdate.setModel("Nintendo Switch");
        consolesUpdate.setManufacturer("Nintendo");
        consolesUpdate.setMemoryAmount("4GB");
        consolesUpdate.setProcessor("Quad-Core Cortex-A57");
        consolesUpdate.setPrice(new BigDecimal("304.98"));
        consolesUpdate.setQuantity(200);

        // Mock updateConsole();
        doNothing().when(consoleDao).updateConsole(consolesUpdate);
        doReturn(consolesUpdate).when(consoleDao).getConsole(17);

        // Mock data for delete
        Consoles consolesDelete = new Consoles();
        consolesDelete.setConsoleId(2);
        consolesDelete.setModel("XBOX 360");
        consolesDelete.setManufacturer("Microsoft");
        consolesDelete.setMemoryAmount("1-Terabyte");
        consolesDelete.setProcessor("8-core AMD Jaguar");
        consolesDelete.setPrice(new BigDecimal("299.00"));
        consolesDelete.setQuantity(28);

        doNothing().when(consoleDao).deleteConsole(2);
        doReturn(null).when(consoleDao).getConsole(2);


        // *** CUSTOM METHODS ***
        // Mock getConsolesByManufacturer();
        List<Consoles> consolesByManufacturerList = new ArrayList<>();
        consolesByManufacturerList.add(consoles);
        doReturn(consolesByManufacturerList).when(consoleDao).getConsolesByManufacturer("Sony");
    }

    @Test
    public void saveConsole() {

        ConsoleViewModel console = new ConsoleViewModel();

        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("8GB");
        console.setProcessor("Jaguar CPU");
        console.setPrice(new BigDecimal("286.99"));
        console.setQuantity(26);

        console = invoiceInventoryService.saveConsole(console);

        ConsoleViewModel fromService = invoiceInventoryService.findConsoleById(console.getConsoleId());
        assertEquals(fromService, console);

    }

    @Test
    public void findAllConsoles() {

        List<ConsoleViewModel> consoleList = invoiceInventoryService.findAllConsoles();
        assertEquals(consoleList.size(), 2);
    }

    @Test
    public void findConsoleById() {

        ConsoleViewModel console = new ConsoleViewModel();

        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("8GB");
        console.setProcessor("Jaguar CPU");
        console.setPrice(new BigDecimal("286.99"));
        console.setQuantity(26);

        console = invoiceInventoryService.saveConsole(console);

        ConsoleViewModel fromService = invoiceInventoryService.findConsoleById(38);
        assertEquals(console, fromService);

    }

    @Test
    public void updateConsole() {

        ConsoleViewModel cvmUpdate = new ConsoleViewModel();

        Consoles consolesUpdate = new Consoles();
        consolesUpdate.setConsoleId(17);
        consolesUpdate.setModel("Nintendo Switch");
        consolesUpdate.setManufacturer("Nintendo");
        consolesUpdate.setMemoryAmount("4GB");
        consolesUpdate.setProcessor("Quad-Core Cortex-A57");
        consolesUpdate.setPrice(new BigDecimal("304.98"));
        consolesUpdate.setQuantity(200);

        cvmUpdate.setConsoleId(consolesUpdate.getConsoleId());
        cvmUpdate.setModel(consolesUpdate.getModel());
        cvmUpdate.setManufacturer(consolesUpdate.getManufacturer());
        cvmUpdate.setMemoryAmount(consolesUpdate.getMemoryAmount());
        cvmUpdate.setProcessor(consolesUpdate.getProcessor());
        cvmUpdate.setPrice(consolesUpdate.getPrice());
        cvmUpdate.setQuantity(consolesUpdate.getQuantity());

        invoiceInventoryService.updateConsole(cvmUpdate);

        ConsoleViewModel cvm2 = invoiceInventoryService.findConsoleById(17);

        Consoles consolesAfterUpdate = new Consoles();
        consolesAfterUpdate.setConsoleId(cvm2.getConsoleId());
        consolesAfterUpdate.setModel(cvm2.getModel());
        consolesAfterUpdate.setManufacturer(cvm2.getManufacturer());
        consolesAfterUpdate.setMemoryAmount(cvm2.getMemoryAmount());
        consolesAfterUpdate.setProcessor(cvm2.getProcessor());
        consolesAfterUpdate.setPrice(cvm2.getPrice());
        consolesAfterUpdate.setQuantity(cvm2.getQuantity());

        assertEquals(consolesAfterUpdate, consolesUpdate);
    }

    @Test
    public void removeConsole() {

        invoiceInventoryService.removeConsole(28);

        ConsoleViewModel cvm = invoiceInventoryService.findConsoleById(28);

        assertNull(cvm);
    }

    @Test
    public void findConsolesByManufacturer() {

        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("8GB");
        console.setProcessor("Jaguar CPU");
        console.setPrice(new BigDecimal("286.99"));
        console.setQuantity(26);

        console = invoiceInventoryService.saveConsole(console);

        List<ConsoleViewModel> consolesByManufacturerList = invoiceInventoryService.findConsolesByManufacturer("Sony");
        assertEquals(1, consolesByManufacturerList.size());

        consolesByManufacturerList = invoiceInventoryService.findConsolesByManufacturer("Atari");
        assertEquals(consolesByManufacturerList.size(), 0);
    }

    private void setUpTShirtDaoMock() {

        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirts tShirts = new TShirts();
        tShirts.setTShirtId(50);
        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts.setPrice(new BigDecimal("14.99"));
        tShirts.setQuantity(21);

        TShirts tShirts1 = new TShirts();
        tShirts1.setColor("purple");
        tShirts1.setSize("XS");
        tShirts1.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts1.setPrice(new BigDecimal("14.99"));
        tShirts1.setQuantity(21);

        // Second mock TShirt Object
        TShirts tShirts2 = new TShirts();
        tShirts2.setTShirtId(51);
        tShirts2.setSize("XS");
        tShirts2.setColor("green");
        tShirts2.setDescription("manga Super Sailor Moon with inner senshi");
        tShirts2.setPrice(new BigDecimal("11.99"));
        tShirts2.setQuantity(11);

        TShirts tShirts2A = new TShirts();
        tShirts2A.setSize("XS");
        tShirts2A.setColor("green");
        tShirts2A.setDescription("manga Super Sailor Moon with inner senshi");
        tShirts2A.setPrice(new BigDecimal("11.99"));
        tShirts2A.setQuantity(11);

        // Mock addTShirt
        doReturn(tShirts).when(tShirtDao).addTShirt(tShirts1);
        doReturn(tShirts2).when(tShirtDao).addTShirt(tShirts2A);

        // Mock getAllTShirts returns 1
        List<TShirts> tShirtsList = new ArrayList<>();
        tShirtsList.add(tShirts);
        doReturn(tShirtsList).when(tShirtDao).getAllTShirts();

        // Mock getGame() return game when gameDao.getGame(int id) is called
        doReturn(tShirts).when(tShirtDao).getTShirt(50);

        TShirts tShirtsUpdate = new TShirts();
        tShirtsUpdate.setTShirtId(52);
        tShirtsUpdate.setSize("S");
        tShirtsUpdate.setColor("white");
        tShirtsUpdate.setDescription("manga Mako Kino sitting on lawn");
        tShirtsUpdate.setPrice(new BigDecimal("15.99"));
        tShirtsUpdate.setQuantity(15);

        // Mock updateGame();
        doNothing().when(tShirtDao).updateTShirt(tShirtsUpdate);
        doReturn(tShirtsUpdate).when(tShirtDao).getTShirt(52);

        // Mock data for delete
        TShirts tShirtsDelete = new TShirts();
        tShirtsDelete.setTShirtId(53);
        tShirtsDelete.setSize("M");
        tShirtsDelete.setColor("pink");
        tShirtsDelete.setDescription("manga Sailor Cosmo and Chibi Chibi");
        tShirtsDelete.setPrice(new BigDecimal("17.99"));
        tShirtsDelete.setQuantity(1);

        doNothing().when(tShirtDao).deleteTShirt(53);
        doReturn(null).when(tShirtDao).getTShirt(53);

        // *** CUSTOM METHODS ***
        // Mock getTShirtsByColor()
        List<TShirts> tShirtsByColorList = new ArrayList<>();
        tShirtsByColorList.add(tShirts);
        doReturn(tShirtsByColorList).when(tShirtDao).getTShirtsByColor("purple");

        // Mock getTShirtsBySize()
        List<TShirts> tShirtsBySizeList = new ArrayList<>();
        tShirtsBySizeList.add(tShirts);
        tShirtsBySizeList.add(tShirts2);
        doReturn(tShirtsBySizeList).when(tShirtDao).getTShirtsBySize("XS");

    }

    @Test
    public void saveTShirt() {

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("XS");
        tShirt.setColor("purple");
        tShirt.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirt.setPrice(new BigDecimal("14.99"));
        tShirt.setQuantity(21);

        tShirt = invoiceInventoryService.saveTShirt(tShirt);

        TShirtViewModel fromService = invoiceInventoryService.findTShirtById(tShirt.getTShirtId());
        assertEquals(fromService, tShirt);

    }

    @Test
    public  void findAllTShirts() {

        List<TShirtViewModel> tShirtList = invoiceInventoryService.findAllTShirts();
        assertEquals(1, tShirtList.size());

    }

    @Test
    public void findTShirtById() {

        TShirtViewModel tShirt = new TShirtViewModel();

        tShirt.setSize("XS");
        tShirt.setColor("purple");
        tShirt.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirt.setPrice(new BigDecimal("14.99"));
        tShirt.setQuantity(21);

        tShirt = invoiceInventoryService.saveTShirt(tShirt);

        TShirtViewModel fromService = invoiceInventoryService.findTShirtById(50);
        assertEquals(tShirt, fromService);

    }

    @Test
    public void updateTShirt() {

        TShirtViewModel tvmUpdate = new TShirtViewModel();

        TShirts tShirtsUpdate = new TShirts();
        tShirtsUpdate.setTShirtId(52);
        tShirtsUpdate.setSize("S");
        tShirtsUpdate.setColor("white");
        tShirtsUpdate.setDescription("manga Mako Kino sitting on lawn");
        tShirtsUpdate.setPrice(new BigDecimal("15.99"));
        tShirtsUpdate.setQuantity(15);

        tvmUpdate.setTShirtId(tShirtsUpdate.getTShirtId());
        tvmUpdate.setSize(tShirtsUpdate.getSize());
        tvmUpdate.setColor(tShirtsUpdate.getColor());
        tvmUpdate.setDescription(tShirtsUpdate.getDescription());
        tvmUpdate.setPrice(tShirtsUpdate.getPrice());
        tvmUpdate.setQuantity(tShirtsUpdate.getQuantity());

        invoiceInventoryService.updateTShirt(tvmUpdate);

        TShirtViewModel tvm2 = invoiceInventoryService.findTShirtById(52);

        TShirts tShirtsAfterUpdate = new TShirts();
        tShirtsAfterUpdate.setTShirtId(tvm2.getTShirtId());
        tShirtsAfterUpdate.setSize(tvm2.getSize());
        tShirtsAfterUpdate.setColor(tvm2.getColor());
        tShirtsAfterUpdate.setDescription(tvm2.getDescription());
        tShirtsAfterUpdate.setPrice(tvm2.getPrice());
        tShirtsAfterUpdate.setQuantity(tvm2.getQuantity());

        assertEquals(tShirtsAfterUpdate, tShirtsUpdate);

    }

    @Test
    public void removeTShirt(){

        invoiceInventoryService.removeTShirt(53);

        TShirtViewModel tvm = invoiceInventoryService.findTShirtById(53);

        assertNull(tvm);
    }

    @Test
    public void findTShirtByColor() {

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("XS");
        tShirt.setColor("purple");
        tShirt.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirt.setPrice(new BigDecimal("14.99"));
        tShirt.setQuantity(21);

        tShirt = invoiceInventoryService.saveTShirt(tShirt);

        List<TShirtViewModel> tShirtByColorList = invoiceInventoryService.findTShirtByColor("purple");
        assertEquals(1, tShirtByColorList.size());

        tShirtByColorList = invoiceInventoryService.findTShirtByColor("black");
        assertEquals(tShirtByColorList.size(), 0);

    }

    @Test
    public void findTShirtsBySize() {

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("XS");
        tShirt.setColor("purple");
        tShirt.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirt.setPrice(new BigDecimal("14.99"));
        tShirt.setQuantity(21);
        tShirt = invoiceInventoryService.saveTShirt(tShirt);

       TShirtViewModel tShirt2 = new TShirtViewModel();
        tShirt2.setSize("XS");
        tShirt2.setColor("green");
        tShirt2.setDescription("manga Super Sailor Moon with inner senshi");
        tShirt2.setPrice(new BigDecimal("11.99"));
        tShirt2.setQuantity(11);
        tShirt2 = invoiceInventoryService.saveTShirt(tShirt2);

        List<TShirtViewModel> tShirtsBySizeList = invoiceInventoryService.findTShirtsBySize("XS");
        assertEquals(2, tShirtsBySizeList.size());

        tShirtsBySizeList = invoiceInventoryService.findTShirtsBySize("LG");
        assertEquals(tShirtsBySizeList.size(), 0);

    }

    private void setUpInvoiceDaoMock() {

        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate str = new SalesTaxRate();
        str.setState("WA");
        str.setRate(new BigDecimal("0.05"));

        ProcessingFee pf = new ProcessingFee();
        pf.setFee(new BigDecimal("1.98"));
        pf.setProductType("T-shirts");

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(70);
        invoice.setName("Bebe S. Dufresne");
        invoice.setStreet("Aloha Street");
        invoice.setCity("Seattle");
        invoice.setState("WA");
        invoice.setZipCode("98101");
        invoice.setItemType("T-shirts");
        invoice.setItemId(50);
        invoice.setUnitPrice(new BigDecimal("14.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("29.98"));
        invoice.setTax(new BigDecimal("1.50"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("33.46"));

        Invoice invoice1 = new Invoice();
        invoice1.setName("Bebe S. Dufresne");
        invoice1.setStreet("Aloha Street");
        invoice1.setCity("Seattle");
        invoice1.setState("WA");
        invoice1.setZipCode("98101");
        invoice1.setItemType("T-shirts");
        invoice1.setItemId(50);
        invoice1.setUnitPrice(new BigDecimal("14.99"));
        invoice1.setQuantity(2);
        invoice1.setSubtotal(new BigDecimal("29.98"));
        invoice1.setTax(new BigDecimal("1.50"));
        invoice1.setProcessingFee(new BigDecimal("1.98"));
        invoice1.setTotal(new BigDecimal("33.46"));


        // Mock addInvoice()
        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(str).when(salesTaxRateDao).getSalesTaxRate("WA");
        doReturn(pf).when(processingFeeDao).getProcessingFee("t-shirts");

        // Mock getAllInvoices()
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();

        // Mock getInvoice()
        doReturn(invoice).when(invoiceDao).getInvoice(70);

        // Mock data for delete
        Invoice invoiceDelete = new Invoice();
        invoiceDelete.setInvoiceId(71);
        invoiceDelete.setName("Gise M. Dufresne");
        invoiceDelete.setStreet("E Ellsworth Avenue");
        invoiceDelete.setCity("Denver");
        invoiceDelete.setState("CO");
        invoiceDelete.setZipCode("80206");
        invoiceDelete.setItemType("Games");
        invoiceDelete.setItemId(100);
        invoiceDelete.setUnitPrice(new BigDecimal("8.99"));
        invoiceDelete.setQuantity(1);
        invoiceDelete.setSubtotal(new BigDecimal("8.99"));
        invoiceDelete.setTax(new BigDecimal(".35"));
        invoiceDelete.setProcessingFee(new BigDecimal("1.49"));
        invoiceDelete.setTotal(new BigDecimal("10.83"));

        doNothing().when(invoiceDao).deleteInvoice(71);
        doReturn(null).when(invoiceDao).getInvoice(71);

    }

    @Test
    public void saveInvoice() {

        InvoiceViewModel invoice = new InvoiceViewModel();

        invoice.setName("Bebe S. Dufresne");
        invoice.setStreet("Aloha Street");
        invoice.setCity("Seattle");
        invoice.setState("WA");
        invoice.setZipCode("98101");
        invoice.setItemType("T-shirts");
        invoice.setItemId(50);
        invoice.setUnitPrice(new BigDecimal("14.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("29.98"));
        invoice.setTax(new BigDecimal("1.50"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("33.46"));

        invoice = invoiceInventoryService.saveInvoice(invoice);

        InvoiceViewModel fromService = invoiceInventoryService.findInvoiceById(invoice.getInvoiceId());
        assertEquals(fromService, invoice);
    }

    @Test
    public void findInvoiceById() {
        InvoiceViewModel invoice = new InvoiceViewModel();

        invoice.setName("Bebe S. Dufresne");
        invoice.setStreet("Aloha Street");
        invoice.setCity("Seattle");
        invoice.setState("WA");
        invoice.setZipCode("98101");
        invoice.setItemType("T-shirts");
        invoice.setItemId(50);
        invoice.setUnitPrice(new BigDecimal("14.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("29.98"));
        invoice.setTax(new BigDecimal("1.50"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("33.46"));

        invoice = invoiceInventoryService.saveInvoice(invoice);

        InvoiceViewModel fromService = invoiceInventoryService.findInvoiceById(70);
        assertEquals(fromService, invoice);
    }

    @Test
    public void findAllInvoices() {

        List<InvoiceViewModel> invoiceList = invoiceInventoryService.findAllInvoices();
        assertEquals(1, invoiceList.size());

    }

    @Test
    public void removeInvoice() {

        invoiceInventoryService.removeInvoice(71);

        InvoiceViewModel ivm = invoiceInventoryService.findInvoiceById(71);

        assertNull(ivm);
    }

}