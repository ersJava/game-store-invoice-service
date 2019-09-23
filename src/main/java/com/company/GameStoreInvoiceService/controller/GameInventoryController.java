package com.company.GameStoreInvoiceService.controller;

import com.company.GameStoreInvoiceService.exception.NotFoundException;
import com.company.GameStoreInvoiceService.service.InvoiceInventoryService;
import com.company.GameStoreInvoiceService.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameInventoryController {

    @Autowired
    InvoiceInventoryService invoiceInventoryService;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game) {

        return invoiceInventoryService.saveGame(game);
    }

    // GET ALL/ READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {

        return invoiceInventoryService.findAllGames();
    }

    // GET BY ID/ READ
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int id){

        GameViewModel gameViewModel = invoiceInventoryService.findGameById(id);
        if (gameViewModel == null)
            throw new NotFoundException("Game could not be retrieved for id " + id);
        return gameViewModel;
    }

    // UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable("id") int id, @RequestBody @Valid GameViewModel game){

        if(game.getGameId() == 0)
            game.setGameId(id);
        if(id != game.getGameId()) {
            throw new IllegalArgumentException("Game ID on path must match the ID in the Game object");
        }
        invoiceInventoryService.updateGame(game);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") int id) {

        invoiceInventoryService.removeGame(id);
    }

    // CUSTOM METHOD GET BY STUDIO
    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio) {

        List<GameViewModel> gvmByStudioList = invoiceInventoryService.findGamesByStudio(studio);
        if(gvmByStudioList != null && gvmByStudioList.size() == 0)
            throw new NotFoundException("No games in the inventory found for " + studio + ", studio");
        return gvmByStudioList;
    }

    // CUSTOM METHOD GET BY ESRB RATING
    @GetMapping("/rating/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByRating(@PathVariable("rating")  String rating) {

        List<GameViewModel> gvmByRatingList = invoiceInventoryService.findGamesByRating(rating);
        if(gvmByRatingList != null && gvmByRatingList.size() == 0)
            throw new NotFoundException("No games in the inventory found for ESRB rating of " + rating);
        return gvmByRatingList;
    }

    // CUSTOM METHOD GET BY TITLE
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGameByTitle(@PathVariable("title") String title) {

        GameViewModel gameByTitle = invoiceInventoryService.getGameByTitle(title);

        if(gameByTitle != null && !gameByTitle.getTitle().equals(title))
            throw new NotFoundException("Game could not be retrieved by " + title + ", title");
        return gameByTitle;
    }
}
