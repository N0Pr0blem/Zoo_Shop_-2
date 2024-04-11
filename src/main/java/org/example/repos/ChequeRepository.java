package org.example.repos;

import org.example.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque,Long> {
    List<Cheque> findByUserId(Long id);
}
