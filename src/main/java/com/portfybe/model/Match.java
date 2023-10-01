package com.portfybe.model;

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
    @JoinColumn(name = "user_id")
    private List<User> users;
    @OneToOne
    @JoinColumn(name = "adresses_id")
    private Adress adresses;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
