package com.bcs.devicemonitoring.service;

import com.bcs.devicemonitoring.dto.DeviceDashboardResponse;
import com.bcs.devicemonitoring.dto.DeviceDetailsResponse;
import com.bcs.devicemonitoring.dto.DeviceRegistrationRequest;
import com.bcs.devicemonitoring.dto.StatusReportRequest;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    UUID registerDevice(DeviceRegistrationRequest request);
    UUID submitStatusReport(UUID deviceId, StatusReportRequest request);
    List<DeviceDashboardResponse> getDashboard();
    DeviceDetailsResponse getDeviceDetails(UUID deviceId);
}
