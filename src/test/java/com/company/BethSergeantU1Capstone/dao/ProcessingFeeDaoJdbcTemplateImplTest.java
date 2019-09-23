package com.company.BethSergeantU1Capstone.dao;

import com.company.GameStoreInvoiceService.dao.ProcessingFeeDao;
import com.company.GameStoreInvoiceService.model.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoJdbcTemplateImplTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Test
    public void getProcessingFee() {

        ProcessingFee pf = new ProcessingFee();
        pf.setProductType("Games");
        pf.setFee(new BigDecimal("1.49"));

        ProcessingFee pf2 = processingFeeDao.getProcessingFee("Games");
        assertEquals(pf, pf2);

    }
}