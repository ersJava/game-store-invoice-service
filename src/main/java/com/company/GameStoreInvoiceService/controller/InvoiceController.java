package com.company.GameStoreInvoiceService.controller;

import com.company.GameStoreInvoiceService.exception.NotFoundException;
import com.company.GameStoreInvoiceService.service.InvoiceInventoryService;
import com.company.GameStoreInvoiceService.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceInventoryService invoiceInventoryService;

    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoice) {

        return invoiceInventoryService.saveInvoice(invoice);
    }

    // GET ALL/ READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {

        return invoiceInventoryService.findAllInvoices();
    }

    // GET BY ID/ READ
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable("id") int id) {

        InvoiceViewModel invoiceViewModel = invoiceInventoryService.findInvoiceById(id);
        if (invoiceViewModel == null)
            throw new NotFoundException("Invoice could not be retrieved for id " + id);
        return invoiceViewModel;
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable("id") int id) {

        invoiceInventoryService.removeInvoice(id);
    }
}
