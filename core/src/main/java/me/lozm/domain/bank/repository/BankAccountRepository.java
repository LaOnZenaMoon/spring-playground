package me.lozm.domain.bank.repository;

import me.lozm.domain.bank.entity.BankAccount;
import me.lozm.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {



}
