package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.TShirts;
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
public class TShirtsDaoJdbcTemplateImplTest {

    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {

        List<TShirts> tShirtsList = tShirtDao.getAllTShirts();
        tShirtsList.stream().forEach(tShirt -> tShirtDao.deleteTShirt(tShirt.getTShirtId()));
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void addGetDeleteTShirt() {

        TShirts tShirts = new TShirts();
        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts.setPrice(new BigDecimal("14.99"));
        tShirts.setQuantity(21);
        tShirts = tShirtDao.addTShirt(tShirts);

        TShirts tShirts2 = tShirtDao.getTShirt(tShirts.getTShirtId());
        assertEquals(tShirts, tShirts2);

        tShirtDao.deleteTShirt(tShirts.getTShirtId());
        tShirts2 = tShirtDao.getTShirt(tShirts.getTShirtId());
        assertNull(tShirts2);
    }

    @Test
    public void getAllTShirts() {

        TShirts tShirts = new TShirts();
        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts.setPrice(new BigDecimal("14.99"));
        tShirts.setQuantity(21);
        tShirtDao.addTShirt(tShirts);

        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Super Sailor Moon with inner senshi, starry background");
        tShirts.setPrice(new BigDecimal("11.99"));
        tShirts.setQuantity(11);
        tShirtDao.addTShirt(tShirts);

        List<TShirts> tShirtsList = tShirtDao.getAllTShirts();
        assertEquals(tShirtsList.size(), 2);
    }

    @Test
    public void updateTShirt() {

        TShirts tShirts = new TShirts();
        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts.setPrice(new BigDecimal("14.99"));
        tShirts.setQuantity(21);
        tShirts =tShirtDao.addTShirt(tShirts);

        tShirts.setSize("UPDATE");
        tShirts.setColor("UPDATE");
        tShirts.setDescription("UPDATE");
        tShirts.setPrice(new BigDecimal("1.99"));
        tShirts.setQuantity(1);
        tShirtDao.updateTShirt(tShirts);

        TShirts tShirts2 = tShirtDao.getTShirt(tShirts.getTShirtId());
        assertEquals(tShirts2, tShirts);

    }

    @Test
    public void getTShirtsByColor() {

        TShirts tShirts = new TShirts();
        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts.setPrice(new BigDecimal("14.99"));
        tShirts.setQuantity(21);
        tShirtDao.addTShirt(tShirts);

        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Super Sailor Moon with inner senshi, starry background");
        tShirts.setPrice(new BigDecimal("11.99"));
        tShirts.setQuantity(11);
        tShirtDao.addTShirt(tShirts);

        List<TShirts> tShirtsList = tShirtDao.getTShirtsByColor("purple");
        assertEquals(tShirtsList.size(), 2);

        tShirtsList = tShirtDao.getTShirtsByColor("blue");
        assertEquals(tShirtsList.size(), 0);

    }

    @Test
    public void getTShirtsBySize() {

        TShirts tShirts = new TShirts();
        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Eternal Sailor Moon looking at moon");
        tShirts.setPrice(new BigDecimal("14.99"));
        tShirts.setQuantity(21);
        tShirtDao.addTShirt(tShirts);

        tShirts.setSize("XS");
        tShirts.setColor("purple");
        tShirts.setDescription("manga Super Sailor Moon with inner senshi, starry background");
        tShirts.setPrice(new BigDecimal("11.99"));
        tShirts.setQuantity(11);
        tShirtDao.addTShirt(tShirts);

        List<TShirts> tShirtsList = tShirtDao.getTShirtsBySize("XS");
        assertEquals(tShirtsList.size(), 2);

        tShirtsList = tShirtDao.getTShirtsBySize("S");
        assertEquals(tShirtsList.size(), 0);
    }
}