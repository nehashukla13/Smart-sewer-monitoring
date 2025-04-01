package com.echo.sensordashboard.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<GasSensor> sensors;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
} 