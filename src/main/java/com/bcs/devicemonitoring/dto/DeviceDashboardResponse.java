package com.bcs.devicemonitoring.dto;

import com.bcs.devicemonitoring.model.DeviceType;
import com.bcs.devicemonitoring.model.OperationalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDashboardResponse {
    private UUID id;
    private String name;
    private DeviceType deviceType;
    private OperationalStatus currentStatus;
    private Instant lastReportAt;
    private boolean isStale;
}
