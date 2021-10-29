package me.lozm.domain.bank.repository;

import me.lozm.domain.bank.entity.TransferHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferHistoryRepository extends JpaRepository<TransferHistory, Long> {



}
