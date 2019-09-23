package com.company.GameStoreInvoiceService.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Consoles {

    private int consoleId;

    @NotEmpty(message = "Please supply a model")
    @Size(min=1, max=50)
    private String model;

    @NotEmpty(message = "Please supply a manufacturer")
    @Size(min=1, max=50)
    private String manufacturer;

    // database indicates it can be null
    private String memoryAmount;

    // database indicates it can be null
    private String processor;

    @NotNull(message = "Please supply a value for the price")
    @Digits(integer = 3, fraction = 2)
    @Min(1)
    private BigDecimal price;

    @NotNull(message="Please supply a value for quantity")
    private int quantity;

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consoles consoles = (Consoles) o;
        return getConsoleId() == consoles.getConsoleId() &&
                getQuantity() == consoles.getQuantity() &&
                getModel().equals(consoles.getModel()) &&
                getManufacturer().equals(consoles.getManufacturer()) &&
                getMemoryAmount().equals(consoles.getMemoryAmount()) &&
                getProcessor().equals(consoles.getProcessor()) &&
                getPrice().equals(consoles.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getPrice(), getQuantity());
    }
}
