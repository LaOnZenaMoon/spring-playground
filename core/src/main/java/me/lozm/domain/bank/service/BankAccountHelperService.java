package me.lozm.domain.bank.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.bank.entity.BankAccount;
import me.lozm.domain.bank.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountHelperService {

    private final BankAccountRepository bankAccountRepository;


    public Optional<BankAccount> findBankAccount(Long bankAccountId) {
        return bankAccountRepository.findById(bankAccountId);
    }

    public BankAccount getBankAccount(Long bankAccountId) {
        return findBankAccount(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 은행 계좌입니다. 은행 계좌: [%s]", bankAccountId)));
    }

}
