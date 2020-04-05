package com.kovunov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "findAllPlayers", query = "SELECT p FROM Player p")
})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int number;
    private String team;
    private String country;

    public Player(String name, int number, String team, String country) {
        this.name = name;
        this.number = number;
        this.team = team;
        this.country = country;
    }
}
