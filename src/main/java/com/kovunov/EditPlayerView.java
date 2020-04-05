package com.kovunov;

import com.kovunov.entity.Player;
import com.kovunov.service.PlayerService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditPlayerView implements Serializable {
    private String name;
    private int number;
    private String team;
    private String country;

    @EJB
    private PlayerService playerService;
    private transient Player playerToUpdate;

    public void populateView(long playerId) {
        playerToUpdate = playerService.findById(playerId);
        this.setName(playerToUpdate.getName());
        this.setCountry(playerToUpdate.getCountry());
        this.setNumber(playerToUpdate.getNumber());
        this.setTeam(playerToUpdate.getTeam());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String save() {
        Player createdPlayer = new Player(name, number, team, country);
        if (playerToUpdate != null) {
            createdPlayer.setId(playerToUpdate.getId());
            playerService.update(createdPlayer);
        } else {
            playerService.create(createdPlayer);
        }
        nullifyFields();
        return "/players.xhtml?faces-redirect=true";
    }

    private void nullifyFields() {
        playerToUpdate = null;
        this.setTeam(null);
        this.setNumber(0);
        this.setCountry(null);
        this.setName(null);
    }
}
