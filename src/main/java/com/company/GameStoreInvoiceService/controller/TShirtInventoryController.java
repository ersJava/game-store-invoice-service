package com.company.GameStoreInvoiceService.controller;

import com.company.GameStoreInvoiceService.exception.NotFoundException;
import com.company.GameStoreInvoiceService.service.InvoiceInventoryService;
import com.company.GameStoreInvoiceService.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tshirt")
public class TShirtInventoryController {

    @Autowired
    InvoiceInventoryService invoiceInventoryService;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirt){

        return invoiceInventoryService.saveTShirt(tShirt);
    }

    // GET ALL/ READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirts() {

        return invoiceInventoryService.findAllTShirts();
    }

    // GET BY ID/ READ
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTShirt(@PathVariable("id") int id) {

        TShirtViewModel tShirtViewModel = invoiceInventoryService.findTShirtById(id);

        if(tShirtViewModel == null)
            throw new NotFoundException("T-Shirt could not be retrieved for id " + id);
        return tShirtViewModel;
    }

    // UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@PathVariable("id") int id, @RequestBody @Valid TShirtViewModel tShirt) {

        if(tShirt.getTShirtId() == 0)
            tShirt.setTShirtId(id);
        if(id != tShirt.getTShirtId()) {
            throw new IllegalArgumentException("T-Shirt ID on path must match the ID in the T-Shirt object");
        }
        invoiceInventoryService.updateTShirt(tShirt);
    }

    // CUSTOM METHOD GET BY COLOR
    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtByColor(@PathVariable("color") String color) {

        List<TShirtViewModel> tvmByColorList = invoiceInventoryService.findTShirtByColor(color);
        if(tvmByColorList != null && tvmByColorList.size() == 0)
            throw new NotFoundException("No T-Shirts in the inventory found for the color " + color);
        return tvmByColorList;

    }

    // CUSTOM METHOD GET BY SIZE
    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtBySize(@PathVariable("size") String size) {

        List<TShirtViewModel> tvmBySizeList = invoiceInventoryService.findTShirtsBySize(size);
        if(tvmBySizeList != null && tvmBySizeList.size() == 0)
            throw new NotFoundException("No T-Shirts in the inventory found for " + size + " size");
        return tvmBySizeList;
    }

}
