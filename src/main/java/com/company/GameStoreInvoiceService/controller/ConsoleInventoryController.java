package com.company.GameStoreInvoiceService.controller;

import com.company.GameStoreInvoiceService.exception.NotFoundException;
import com.company.GameStoreInvoiceService.service.InvoiceInventoryService;
import com.company.GameStoreInvoiceService.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/console")
public class ConsoleInventoryController {

    @Autowired
    InvoiceInventoryService invoiceInventoryService;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel console) {

        return invoiceInventoryService.saveConsole(console);
    }

    // GET ALL/ READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles() {

        return invoiceInventoryService.findAllConsoles();
    }

    // GET BY ID/ READ
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int id) {

        ConsoleViewModel consoleViewModel = invoiceInventoryService.findConsoleById(id);

        if(consoleViewModel == null)
            throw new NotFoundException("Console could not be retrieved for id " + id);
        return consoleViewModel;
    }

    // UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable("id") int id, @RequestBody @Valid ConsoleViewModel console) {

        if(console.getConsoleId() == 0)
            console.setConsoleId(id);
            if(id != console.getConsoleId()) {
                throw new IllegalArgumentException("Console ID on path must match the ID in the Game object");
            }
            invoiceInventoryService.updateConsole(console);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") int id) {

        invoiceInventoryService.removeConsole(id);
    }

    // CUSTOM METHOD GET BY MANUFACTURER
    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable("manufacturer") String manufacturer) {

        List<ConsoleViewModel> cvmByManufacturerList = invoiceInventoryService.findConsolesByManufacturer(manufacturer);

        if(cvmByManufacturerList != null && cvmByManufacturerList.size() == 0)
            throw new NotFoundException("No consoles in the inventory found for manufacturer " + manufacturer + ".");
        return cvmByManufacturerList;
    }

}
