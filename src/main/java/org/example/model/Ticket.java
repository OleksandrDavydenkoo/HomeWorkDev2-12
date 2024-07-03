package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.*;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "from_planet_id")
    private String fromPlanetId;
    @Column(name = "to_planet_id")
    private String toPlanetId;
}
