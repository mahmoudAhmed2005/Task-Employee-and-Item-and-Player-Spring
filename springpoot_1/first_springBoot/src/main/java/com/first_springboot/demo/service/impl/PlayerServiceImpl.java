package com.first_springboot.demo.service.impl;

import com.first_springboot.demo.model.Player;
import com.first_springboot.demo.repo.PlayerRepo;
import com.first_springboot.demo.service.PlayerService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepo playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo){

        this.playerRepo=playerRepo;
    }


    @Override
    public Player savePlayer(Player player) throws SystemException {

        //this check for id should be null
        if(Objects.nonNull(player.getId())){
            throw  new SystemException("id mast be null");
        }

        //this check for salary should be found
        if(Objects.isNull(player.getSalary())){
            throw  new SystemException("salary mast be found");
        }

        //this check for number should be found
        if(Objects.isNull(player.getNumber())){
            throw  new SystemException("number mast be found");
        }
         //this check for name should be found
        if(Objects.isNull(player.getName())){
            throw  new SystemException("name mast be found");
        }

        //this check for name not be doublicated name
        Optional<Player> playerOptional = playerRepo.findByName(player.getName());
        if (playerOptional.isPresent()){
            throw new SystemException("userName exsist by name" + player.getName());
        }
        return playerRepo.save(player);
    }


    @Override
    public Player updatePlayer(Player player) throws SystemException {
        //this check for id should be found
        if(Objects.isNull(player.getId())){
            throw  new SystemException("id mast be not null");
        }


        Optional<Player> playerOptional = playerRepo.findById(player.getId());
        if (playerOptional.isEmpty()){
            throw  new SystemException("id mast be not null");
        }


        if (playerOptional.get().getName().equals(player.getName())){
            return playerRepo.save(player);
        }

        playerOptional = playerRepo.findByName(player.getName());
        if (playerOptional.isPresent()){
            throw new SystemException("userName exsist by name" + player.getName());
        }


        return playerRepo.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player>players = playerRepo.findAll();
        players.stream().forEach(player -> player.setDetails(player.getName() +  player.getId()));

        return players;
    }

    @Override
    public void removePlayer(Long id) {
        playerRepo.deleteById(id);
    }

    @Override
    public Player getPlayerById(Long id) throws SystemException {
//       Optional<Player> player= playerRepo.findById(id);
//
//        return player.get();

        //=====
        return playerRepo.findById(id).get();

//       if (player.isEmpty()){
//           throw new SystemException("player not found with id" + id);
//       }


    }

    @Override
    public Player getPlayerByName(String name)  {

        return playerRepo.findByName(name).get();
    }

    @Override
    public List<Player> searchName(String name) {
        return playerRepo.findByNameContainingIgnoreCase(name).get();
    }


}
