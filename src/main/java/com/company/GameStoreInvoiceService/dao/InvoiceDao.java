package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    void deleteInvoice(int id);
}
