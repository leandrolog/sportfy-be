package com.sportfybe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Schedule")
@AllArgsConstructor
@NoArgsConstructor
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinColumn(name = "id")
    private List<User> users;
    @OneToOne
    @JoinColumn(name = "id")
    private Address adresses;
    @OneToOne
    private Schedule schedule;
    private Integer slot;
}
