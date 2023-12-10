package org.salon.repo;

import org.salon.models.Reviews;
import org.salon.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoReviews extends JpaRepository<Reviews, Long> {
    List<Reviews> findByService(Services service);
}
