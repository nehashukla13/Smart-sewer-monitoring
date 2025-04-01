package com.echo.sensordashboard.repository;

import com.echo.sensordashboard.model.GasSensor;
import com.echo.sensordashboard.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface GasSensorRepository extends JpaRepository<GasSensor, Long> {
    List<GasSensor> findByZone(Zone zone);
    List<GasSensor> findByZoneAndTimestampBetween(Zone zone, LocalDateTime start, LocalDateTime end);
    List<GasSensor> findByHydrogenSulfideGreaterThan(Double threshold);
} 