package com.echo.sensordashboard.service;

import com.echo.sensordashboard.model.GasSensor;
import com.echo.sensordashboard.model.Zone;
import com.echo.sensordashboard.repository.GasSensorRepository;
import com.echo.sensordashboard.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class responsible for handling sensor data and zone management operations.
 * Acts as the business logic layer between controllers and repositories.
 */
@Service
@RequiredArgsConstructor
public class SensorService {
    // Repository dependencies for data access
    private final GasSensorRepository gasSensorRepository;
    private final ZoneRepository zoneRepository;

    /**
     * Saves a new sensor reading with current timestamp.
     * @param sensor The gas sensor reading to save
     * @return The saved sensor reading with timestamp
     */
    @Transactional
    public GasSensor saveSensorReading(GasSensor sensor) {
        sensor.setTimestamp(LocalDateTime.now());
        return gasSensorRepository.save(sensor);
    }

    /**
     * Retrieves all sensor readings for a specific zone.
     * @param zoneId The ID of the zone
     * @return List of sensor readings for the zone
     * @throws RuntimeException if zone is not found
     */
    public List<GasSensor> getZoneReadings(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        return gasSensorRepository.findByZone(zone);
    }

    /**
     * Retrieves sensor readings for a zone within a specific time range.
     * @param zoneId The ID of the zone
     * @param start Start of the time range
     * @param end End of the time range
     * @return List of sensor readings within the time range
     * @throws RuntimeException if zone is not found
     */
    public List<GasSensor> getZoneReadingsByTimeRange(Long zoneId, LocalDateTime start, LocalDateTime end) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        return gasSensorRepository.findByZoneAndTimestampBetween(zone, start, end);
    }

    /**
     * Retrieves all sensor readings where hydrogen sulfide level exceeds the threshold.
     * @param threshold The threshold value for hydrogen sulfide
     * @return List of sensor readings exceeding the threshold
     */
    public List<GasSensor> getHighGasReadings(Double threshold) {
        return gasSensorRepository.findByHydrogenSulfideGreaterThan(threshold);
    }

    /**
     * Creates a new zone in the system.
     * @param zone The zone to create
     * @return The created zone
     */
    @Transactional
    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    /**
     * Retrieves all zones associated with a specific city.
     * @param cityId The ID of the city
     * @return List of zones in the city
     */
    public List<Zone> getZonesByCity(Long cityId) {
        return zoneRepository.findByCity_Id(cityId);
    }
} 