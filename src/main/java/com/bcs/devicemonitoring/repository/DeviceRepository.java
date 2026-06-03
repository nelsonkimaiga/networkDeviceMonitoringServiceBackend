package com.bcs.devicemonitoring.repository;

import com.bcs.devicemonitoring.dto.DeviceDashboardResponse;
import com.bcs.devicemonitoring.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    
    @Query("""
           SELECT new com.bcs.devicemonitoring.dto.DeviceDashboardResponse(
               d.id, d.name, d.deviceType, sr.status, sr.createdAt,
               (sr.createdAt IS NULL OR sr.createdAt < :staleThreshold)
           )
           FROM Device d
           LEFT JOIN d.statusReports sr
           WHERE sr.createdAt = (SELECT MAX(sr2.createdAt) FROM StatusReport sr2 WHERE sr2.device = d)
           OR sr.createdAt IS NULL
           """)
    List<DeviceDashboardResponse> findDeviceSummaries(java.time.Instant staleThreshold);
}