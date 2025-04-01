package com.echo.sensordashboard.controller;

import com.echo.sensordashboard.model.GasSensor;
import com.echo.sensordashboard.model.Zone;
import com.echo.sensordashboard.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/reading")
    public ResponseEntity<GasSensor> saveSensorReading(@RequestBody GasSensor sensor) {
        return ResponseEntity.ok(sensorService.saveSensorReading(sensor));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<GasSensor>> getZoneReadings(@PathVariable Long zoneId) {
        return ResponseEntity.ok(sensorService.getZoneReadings(zoneId));
    }

    @GetMapping("/zone/{zoneId}/range")
    public ResponseEntity<List<GasSensor>> getZoneReadingsByTimeRange(
            @PathVariable Long zoneId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(sensorService.getZoneReadingsByTimeRange(zoneId, start, end));
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<GasSensor>> getHighGasReadings(@RequestParam Double threshold) {
        return ResponseEntity.ok(sensorService.getHighGasReadings(threshold));
    }

    @PostMapping("/zones")
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        return ResponseEntity.ok(sensorService.createZone(zone));
    }

    @GetMapping("/zones/city/{cityId}")
    public ResponseEntity<List<Zone>> getZonesByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(sensorService.getZonesByCity(cityId));
    }
} 