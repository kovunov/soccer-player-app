package com.kovunov.service;

import com.kovunov.entity.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PlayerService {
    @PersistenceContext(unitName = "playerPersistenceUnit")
    private EntityManager manager;

    public List<Player> getAll() {
        return manager.createNamedQuery("findAllPlayers", Player.class).getResultList();
    }


    public Player findById(long id) {
        return manager.find(Player.class, id);
    }

    public void update(Player player) {
        manager.getTransaction().begin();
        manager.merge(player);
        manager.getTransaction().commit();
    }

    public void create(Player player) {
        manager.getTransaction().begin();
        manager.persist(player);
        manager.getTransaction().commit();
    }

    public void delete(Player player) {
        manager.getTransaction().begin();
        if (!manager.contains(player)) {
            player = manager.merge(player);
        }

        manager.remove(player);
        manager.getTransaction().commit();
    }
}
