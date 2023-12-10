package org.salon.repo;

import org.salon.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoServices extends JpaRepository<Services, Long> {
    List<Services> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Services> findByServiceNameContaining(String title);
    List<Services> findByCategoryContaining(String category);
}
