package com.bcs.devicemonitoring.repository;

import com.bcs.devicemonitoring.model.Device;
import com.bcs.devicemonitoring.model.StatusReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatusReportRepository extends JpaRepository<StatusReport, UUID> {
    List<StatusReport> findByDeviceOrderByCreatedAtDesc(Device device, Pageable pageable);
}
