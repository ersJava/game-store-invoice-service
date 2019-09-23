package com.company.GameStoreInvoiceService.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice {

    private int invoiceId;

    @NotEmpty(message = "Please supply a for name for the order")
    @Size(min=1, max=80)
    private String name;

    @NotEmpty(message = "Please supply a street address for the order")
    @Size(min=1, max=30)
    private String street;

    @NotEmpty(message = "Please supply a city for the order")
    @Size(min=1, max=30)
    private String city;

    @NotEmpty(message = "Please supply a value for state code")
    private String state;

    @NotEmpty(message = "Please supply a zip code for the order")
    @Size(min=5, max=5, message = "Zip code must be 5 digits long.")
    private String zipCode;

    @NotEmpty(message = "Please supply an item type for the order")
    @Size(min=1, max=20)
    private String itemType;

    @NotNull(message = "Please supply a value for the item ID")
    private int itemId;

    // Order quantity must be greater than zero.
    @NotNull(message = "Please supply a value for the quantity")
    @Min(value = 1, message = "There must be a quantity for the order")
    private int quantity;


    @Digits(integer = 3, fraction = 2)
    private BigDecimal unitPrice;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal subtotal;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal tax;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal processingFee;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal total;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getItemId() == invoice.getItemId() &&
                getQuantity() == invoice.getQuantity() &&
                getName().equals(invoice.getName()) &&
                getStreet().equals(invoice.getStreet()) &&
                getCity().equals(invoice.getCity()) &&
                getState().equals(invoice.getState()) &&
                getZipCode().equals(invoice.getZipCode()) &&
                getItemType().equals(invoice.getItemType()) &&
                getUnitPrice().equals(invoice.getUnitPrice()) &&
                getSubtotal().equals(invoice.getSubtotal()) &&
                getTax().equals(invoice.getTax()) &&
                getProcessingFee().equals(invoice.getProcessingFee()) &&
                getTotal().equals(invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }
}
