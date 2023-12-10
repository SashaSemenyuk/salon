package org.salon.repo;

import org.salon.models.MasterServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoMasterServices extends JpaRepository<MasterServices, Long> {
    List<MasterServices> findByService_ServiceId(Long serviceId);
}
