package com.bcs.devicemonitoring.dto;

import com.bcs.devicemonitoring.model.DeviceType;
import com.bcs.devicemonitoring.model.OperationalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDetailsResponse {
    private UUID id;
    private String name;
    private DeviceType deviceType;
    private String ipAddress;
    private String location;
    private Instant registeredAt;
    private List<ReportSummary> recentReports;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReportSummary {
        private UUID id;
        private OperationalStatus status;
        private String message;
        private Instant createdAt;
    }
}
