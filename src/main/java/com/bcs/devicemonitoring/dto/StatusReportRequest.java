package com.bcs.devicemonitoring.dto;

import com.bcs.devicemonitoring.model.OperationalStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusReportRequest {
    
    @NotNull(message = "Status is required")
    private OperationalStatus status;
    
    private String message;
}
