package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.SalesTaxRate;

public interface SalesTaxRateDao {

    SalesTaxRate getSalesTaxRate(String state);

}
