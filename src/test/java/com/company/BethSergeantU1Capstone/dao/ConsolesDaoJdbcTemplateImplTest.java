package com.company.BethSergeantU1Capstone.dao;

import com.company.GameStoreInvoiceService.dao.ConsoleDao;
import com.company.GameStoreInvoiceService.model.Consoles;
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
public class ConsolesDaoJdbcTemplateImplTest {

    @Autowired
    protected ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception {

        List<Consoles> consolesList = consoleDao.getAllConsoles();
        consolesList.stream().forEach(console -> consoleDao.deleteConsole(console.getConsoleId()));
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void addGetDeleteConsole() {

        Consoles consoles = new Consoles();
        consoles.setModel("PlayStation 4");
        consoles.setManufacturer("Sony");
        consoles.setMemoryAmount("8GB");
        consoles.setProcessor("Jaguar CPU");
        consoles.setPrice(new BigDecimal("286.99"));
        consoles.setQuantity(26);
        consoles = consoleDao.addConsole(consoles);

        Consoles consoles2 = consoleDao.getConsole(consoles.getConsoleId());
        assertEquals(consoles, consoles2);

        consoleDao.deleteConsole(consoles.getConsoleId());
        consoles2 = consoleDao.getConsole(consoles.getConsoleId());
        assertNull(consoles2);
    }

    @Test
    public void getAllConsoles() {

        Consoles consoles = new Consoles();
        consoles.setModel("PlayStation 4");
        consoles.setManufacturer("Sony");
        consoles.setMemoryAmount("8GB");
        consoles.setProcessor("Jaguar CPU");
        consoles.setPrice(new BigDecimal("286.99"));
        consoles.setQuantity(26);
        consoleDao.addConsole(consoles);

        consoles = new Consoles();
        consoles.setModel("Super Nintendo");
        consoles.setManufacturer("Nintendo");
        consoles.setMemoryAmount("128Mbit");
        consoles.setProcessor("Ricoh 5A22");
        consoles.setPrice(new BigDecimal("101.12"));
        consoles.setQuantity(12);
        consoleDao.addConsole(consoles);

        List<Consoles> consolesList = consoleDao.getAllConsoles();
        assertEquals(consolesList.size(), 2);
    }

    @Test
    public void updateConsole() {

        Consoles consoles = new Consoles();
        consoles.setModel("PlayStation 4");
        consoles.setManufacturer("Sony");
        consoles.setMemoryAmount("8GB");
        consoles.setProcessor("Jaguar CPU");
        consoles.setPrice(new BigDecimal("286.99"));
        consoles.setQuantity(26);
        consoles = consoleDao.addConsole(consoles);

        consoles.setModel("PS4");
        consoles.setManufacturer("Sony Corporation");
        consoles.setMemoryAmount("UPDATE");
        consoles.setProcessor("UPDATE");
        consoles.setPrice(new BigDecimal("2.99"));
        consoles.setQuantity(1);
        consoleDao.updateConsole(consoles);

        Consoles consoles2 = consoleDao.getConsole(consoles.getConsoleId());
        assertEquals(consoles2, consoles);
    }


    @Test
    public void getConsolesByManufacturer() {

        Consoles consoles = new Consoles();
        consoles.setModel("Nintendo Switch");
        consoles.setManufacturer("Nintendo");
        consoles.setMemoryAmount("4GB");
        consoles.setProcessor("Quad-Core Cortex-A57");
        consoles.setPrice(new BigDecimal("304.98"));
        consoles.setQuantity(200);
        consoles = consoleDao.addConsole(consoles);

        Consoles consoles2 = new Consoles();
        consoles2.setModel("Super Nintendo");
        consoles2.setManufacturer("Nintendo");
        consoles2.setMemoryAmount("128Mbit");
        consoles2.setProcessor("Ricoh 5A22");
        consoles2.setPrice(new BigDecimal("101.12"));
        consoles2.setQuantity(12);
        consoles2 = consoleDao.addConsole(consoles2);

        List<Consoles> consolesList = consoleDao.getConsolesByManufacturer("Nintendo");
        assertEquals(consolesList.size(), 2);

        consolesList = consoleDao.getConsolesByManufacturer("Microsoft");
        assertEquals(consolesList.size(), 0);
    }
}