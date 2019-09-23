package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.Invoice;
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
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    protected InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addGetDeleteInvoice() {

        Invoice invoice = new Invoice();
        invoice.setName("Bebe S. Dufresne");
        invoice.setStreet("Aloha Street");
        invoice.setCity("Seattle");
        invoice.setState("Washington");
        invoice.setZipCode("98101");
        invoice.setItemType("T-shirt");
        invoice.setItemId(50);
        invoice.setUnitPrice(new BigDecimal("11.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("23.98"));
        invoice.setTax(new BigDecimal("1.19"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("27.15"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice, invoice2);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice2);

    }

    @Test
    public void getAllInvoices() {

        Invoice invoice = new Invoice();
        invoice.setName("Bebe S. Dufresne");
        invoice.setStreet("Aloha Street");
        invoice.setCity("Seattle");
        invoice.setState("Washington");
        invoice.setZipCode("98101");
        invoice.setItemType("T-shirt");
        invoice.setItemId(50);
        invoice.setUnitPrice(new BigDecimal("11.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("23.98"));
        invoice.setTax(new BigDecimal("1.19"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("27.15"));
        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setName("Gise M. Dufresne");
        invoice.setStreet("E Ellsworth Avenue");
        invoice.setCity("Denver");
        invoice.setState("Colorado");
        invoice.setZipCode("80206");
        invoice.setItemType("Game");
        invoice.setItemId(100);
        invoice.setUnitPrice(new BigDecimal("8.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("8.99"));
        invoice.setTax(new BigDecimal(".35"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("10.83"));
        invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(invoiceList.size(), 2);

    }

}