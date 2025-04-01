package com.echo.sensordashboard.repository;

import com.echo.sensordashboard.model.City;
import com.echo.sensordashboard.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByCity(City city);
    List<Zone> findByNameContainingIgnoreCase(String name);
    List<Zone> findByCity_Id(Long cityId);
} 