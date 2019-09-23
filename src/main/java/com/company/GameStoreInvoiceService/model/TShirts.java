package com.company.GameStoreInvoiceService.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirts {

    private int tShirtId;

    @NotEmpty(message = "Please supply a size for t-shirt")
    private String size;

    @NotEmpty(message = "Please supply a color for t-shirt")
    @Size(min=1, max=20)
    private String color;

    @NotEmpty(message = "Please supply a description")
    @Size(min=1, max=255, message = "255 character description max.")
    private String description;

    @NotNull(message = "Please supply a value for price")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Please supply a value for quantity")
    private int quantity;

    public int getTShirtId() {
        return tShirtId;
    }

    public void setTShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        TShirts tShirts = (TShirts) o;
        return getTShirtId() == tShirts.getTShirtId() &&
                getQuantity() == tShirts.getQuantity() &&
                getSize().equals(tShirts.getSize()) &&
                getColor().equals(tShirts.getColor()) &&
                getDescription().equals(tShirts.getDescription()) &&
                getPrice().equals(tShirts.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTShirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
