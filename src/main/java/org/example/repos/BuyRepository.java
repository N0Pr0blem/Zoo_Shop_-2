package org.example.repos;

import org.example.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyRepository extends JpaRepository<Buy,Long> {
}
