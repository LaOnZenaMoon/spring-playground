package me.lozm.domain.bank.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.user.entity.User;
import me.lozm.global.code.TransferStatus;
import me.lozm.global.code.converter.TransferStatusConverter;

import javax.persistence.*;

@Entity
@Table(schema = "LOZM", name = "TRANSFER_HISTORY")
@SequenceGenerator(name = "TRANSFER_HISTORY_SEQ_GEN", sequenceName = "TRANSFER_HISTORY_SEQ", initialValue = 1, allocationSize = 50)

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSFER_HISTORY_SEQ_GEN")
    @Column(name = "TRANSFER_HISTORY_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEND_USER_ID", insertable = false, updatable = false)
    private User sendUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEND_BANK_ACCOUNT_ID", insertable = false, updatable = false)
    private BankAccount sendUserBankAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_USER_ID", insertable = false, updatable = false)
    private User receiveUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_BANK_ACCOUNT_ID", insertable = false, updatable = false)
    private BankAccount receiveUserBankAccount;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "STATUS")
    @Convert(converter = TransferStatusConverter.class)
    private TransferStatus status;


    public TransferHistory(BankAccount sendUserBankAccount, BankAccount receiveUserBankAccount, Double amount) {
        this.sendUser = sendUserBankAccount.getOwner();
        this.sendUserBankAccount = sendUserBankAccount;
        this.receiveUser = receiveUserBankAccount.getOwner();
        this.receiveUserBankAccount = receiveUserBankAccount;
        this.amount = amount;
    }

}
