package com.first_springboot.demo.controller;


import com.first_springboot.demo.model.Player;
import com.first_springboot.demo.service.PlayerService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController   // Controller use jason value


public class PlayerController {

    @Autowired
    private PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }





    @GetMapping("/players")
public List<Player> getAllPlayers(){

        return playerService.getAllPlayers();
}


@PostMapping("/players")

    public Player addPlayer(@RequestBody Player player) throws SystemException {

      return   playerService.savePlayer(player);
}


@PutMapping("/players")

    public Player updatePlayer(@RequestBody Player player) throws SystemException {
        return playerService.updatePlayer(player);
}


 //   http://localhost:8080/players?id=13
 /*@DeleteMapping("/players")
    public void deletePlayer(@RequestParam Long id){
     playerService.removePlayer(id);

 }*/


    //or

    //   http://localhost:8080/players/13
    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id){
     playerService.removePlayer(id);

 }




 @GetMapping("/players/{id}")

    public Player getPlayerById(@PathVariable Long id) throws SystemException {
       return playerService.getPlayerById(id);
 }


 @GetMapping("/players/name/{name}")

    public Player getPlayerByName(@PathVariable String name){

        return playerService.getPlayerByName(name);

 }


    @GetMapping("/players/search/{name}")

    public List<Player>  searchName(@PathVariable String name){

        return playerService. searchName(name);
}


}
