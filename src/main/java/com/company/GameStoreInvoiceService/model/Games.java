package com.company.GameStoreInvoiceService.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Games {

    private int gameId;

    @NotEmpty(message = "Please supply a game title")
    @Size(min=1, max=50)
    private String title;

    @NotEmpty(message = "Please supply an ESRB rating")
    @Size(min=1, max=50)
    private String ESRBRating;

    @NotEmpty(message = "Please supply a game description")
    @Size(min=1, max=255, message = "255 character description max.")
    private String description;

    @NotNull(message="Please supply a value for the price")
    @Digits(integer = 3, fraction = 2)
    @Min(1)
    private BigDecimal price;

    @NotEmpty(message = "Please supply a name for studio")
    @Size(min=1, max=50)
    private String studio;

    @NotNull(message="Please supply a value for quantity")
    private int quantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getESRBRating() {
        return ESRBRating;
    }

    public void setESRBRating(String ESRBRating) {
        this.ESRBRating = ESRBRating;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        Games game = (Games) o;
        return getGameId() == game.getGameId() &&
                getQuantity() == game.getQuantity() &&
                getTitle().equals(game.getTitle()) &&
                getESRBRating().equals(game.getESRBRating()) &&
                getDescription().equals(game.getDescription()) &&
                getPrice().equals(game.getPrice()) &&
                getStudio().equals(game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getESRBRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }
}