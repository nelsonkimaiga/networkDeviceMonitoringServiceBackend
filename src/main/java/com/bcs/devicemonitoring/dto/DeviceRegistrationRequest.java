package com.bcs.devicemonitoring.dto;

import com.bcs.devicemonitoring.model.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceRegistrationRequest {
    
    @NotBlank(message = "Device name is required")
    private String name;
    
    @NotNull(message = "Device type is required")
    private DeviceType deviceType;
    
    @NotBlank(message = "IP Address is required")
    private String ipAddress;
    
    @NotBlank(message = "Location is required")
    private String location;
}
