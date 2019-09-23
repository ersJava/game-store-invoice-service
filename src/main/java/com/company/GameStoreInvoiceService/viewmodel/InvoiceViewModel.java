package com.company.GameStoreInvoiceService.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;


public class InvoiceViewModel {

    private int invoiceId;

    @NotEmpty(message="Please supply a for name for the order")
    private String name;

    @NotEmpty(message="Please supply a street address for the order")
    private String street;

    @NotEmpty(message="Please supply a city for the order")
    private String city;

    @NotEmpty(message="Please supply a value for state code")
    @Size(min=2, max=2, message = "Please enter a valid state code abbreviation(2 char format)")
    private String state;

    @NotEmpty(message="Please supply a zip code for the order")
    private String zipCode;

    @NotEmpty(message="Please supply an item type for the order")
    private String itemType;

    @NotNull(message="Please supply a value for the item ID")
    private int itemId;

    @NotNull(message="Please supply a value for the quantity")
    @Min(value = 1, message = "There must be a quantity for the order")
    private int quantity;

    // Response with product information with below calculations
    @Digits(integer = 3, fraction = 2)
    private BigDecimal unitPrice;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal subtotal; // calculation

    @Digits(integer = 3, fraction = 2)
    private BigDecimal tax; // based off state given above

    @Digits(integer = 3, fraction = 2)
    private BigDecimal processingFee; // based on itemType

    @Digits(integer = 3, fraction = 2)
    private BigDecimal total; // calculation

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getInvoiceId() == that.getInvoiceId() &&
                getItemId() == that.getItemId() &&
                getQuantity() == that.getQuantity() &&
                getName().equals(that.getName()) &&
                getStreet().equals(that.getStreet()) &&
                getCity().equals(that.getCity()) &&
                getState().equals(that.getState()) &&
                getZipCode().equals(that.getZipCode()) &&
                getItemType().equals(that.getItemType()) &&
                getUnitPrice().equals(that.getUnitPrice()) &&
                getSubtotal().equals(that.getSubtotal()) &&
                getTax().equals(that.getTax()) &&
                getProcessingFee().equals(that.getProcessingFee()) &&
                getTotal().equals(that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItemId(), getQuantity(), getUnitPrice(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }
}
