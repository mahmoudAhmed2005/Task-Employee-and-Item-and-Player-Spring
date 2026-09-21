package com.first_springboot.demo.service;

import com.first_springboot.demo.model.Player;
import jakarta.transaction.SystemException;

import java.util.List;

public interface PlayerService {


    Player savePlayer(Player player) throws SystemException;

    Player updatePlayer(Player player) throws SystemException;

    List<Player> getAllPlayers();

    void removePlayer(Long id);

    Player getPlayerById(Long id) throws SystemException;

    Player getPlayerByName(String name);

    List<Player>searchName(String name);
}
