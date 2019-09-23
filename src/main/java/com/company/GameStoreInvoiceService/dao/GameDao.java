package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.Games;

import java.util.List;

public interface GameDao {

    // CRUD
    Games addGame(Games game);

    Games getGame(int id);

    List<Games> getAllGames();

    void updateGame(Games game);

    void deleteGame(int id);

    // Custom methods
    List<Games> getGamesByStudio(String studio);

    List<Games> getGamesByESRBRating(String rating);

    Games getGameByTitle(String title);
}
