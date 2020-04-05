package com.kovunov;

import com.kovunov.entity.Player;
import com.kovunov.service.PlayerService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AllPlayersView {
    private List<Player> players;

    @EJB
    private PlayerService playerService;

    @PostConstruct
    public void init() {
        players = new ArrayList<>();
        players.addAll(playerService.getAll());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String deletePlayer(long id) {
        playerService.delete(playerService.findById(id));
        return "/players.xhtml?faces-redirect=true";
    }


    public String redirectToEditPlayer() {
        return "/editPlayer.xhtml?faces-redirect=true";
    }

}
