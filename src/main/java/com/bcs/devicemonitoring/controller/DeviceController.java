package com.bcs.devicemonitoring.controller;

import com.bcs.devicemonitoring.dto.DeviceDashboardResponse;
import com.bcs.devicemonitoring.dto.DeviceDetailsResponse;
import com.bcs.devicemonitoring.dto.DeviceRegistrationRequest;
import com.bcs.devicemonitoring.dto.StatusReportRequest;
import com.bcs.devicemonitoring.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<UUID> registerDevice(@Valid @RequestBody DeviceRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.registerDevice(request));
    }

    @PostMapping(path = "/{deviceId}/reports", consumes = "application/json")
    public ResponseEntity<UUID> submitReport(
            @PathVariable UUID deviceId, 
            @Valid @RequestBody StatusReportRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.submitStatusReport(deviceId, request));
    }

    @GetMapping(path = "/dashboard")
    public ResponseEntity<List<DeviceDashboardResponse>> getDashboard() {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.getDashboard());
    }

    @GetMapping(path = "/{deviceId}")
    public ResponseEntity<DeviceDetailsResponse> getDeviceDetails(@PathVariable UUID deviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.getDeviceDetails(deviceId));
    }
}
