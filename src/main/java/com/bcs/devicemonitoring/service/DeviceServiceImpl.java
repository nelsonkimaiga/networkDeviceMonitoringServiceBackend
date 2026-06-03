package com.bcs.devicemonitoring.service;

import com.bcs.devicemonitoring.dto.DeviceDashboardResponse;
import com.bcs.devicemonitoring.dto.DeviceDetailsResponse;
import com.bcs.devicemonitoring.dto.DeviceRegistrationRequest;
import com.bcs.devicemonitoring.dto.StatusReportRequest;
import com.bcs.devicemonitoring.exception.DeviceNotFoundException;
import com.bcs.devicemonitoring.model.Device;
import com.bcs.devicemonitoring.model.StatusReport;
import com.bcs.devicemonitoring.repository.DeviceRepository;
import com.bcs.devicemonitoring.repository.StatusReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final StatusReportRepository statusReportRepository;

    @Override
    public UUID registerDevice(DeviceRegistrationRequest request) {
        Device device = Device.builder()
                .name(request.getName())
                .deviceType(request.getDeviceType())
                .ipAddress(request.getIpAddress())
                .location(request.getLocation())
                .build();
        return deviceRepository.save(device).getId();
    }

    @Override
    public UUID submitStatusReport(UUID deviceId, StatusReportRequest request) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        StatusReport report = StatusReport.builder()
                .device(device)
                .status(request.getStatus())
                .message(request.getMessage())
                .build();

        return statusReportRepository.save(report).getId();
    }

    @Override
    public List<DeviceDashboardResponse> getDashboard() {
        Instant staleThreshold = Instant.now().minus(Duration.ofMinutes(15));
        return deviceRepository.findDeviceSummaries(staleThreshold);
    }

    @Override
    public DeviceDetailsResponse getDeviceDetails(UUID deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        List<StatusReport> reports = statusReportRepository.findByDeviceOrderByCreatedAtDesc(
                device, PageRequest.of(0, 20));

        List<DeviceDetailsResponse.ReportSummary> reportSummaries = reports.stream()
                .map(r -> DeviceDetailsResponse.ReportSummary.builder()
                        .id(r.getId())
                        .status(r.getStatus())
                        .message(r.getMessage())
                        .createdAt(r.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return DeviceDetailsResponse.builder()
                .id(device.getId())
                .name(device.getName())
                .deviceType(device.getDeviceType())
                .ipAddress(device.getIpAddress())
                .location(device.getLocation())
                .registeredAt(device.getRegisteredAt())
                .recentReports(reportSummaries)
                .build();
    }
}
