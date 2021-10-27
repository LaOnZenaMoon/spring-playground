package me.lozm.domain.bank.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(schema = "LOZM", name = "BANK_ACCOUNT")
@SequenceGenerator(name = "BANK_SEQ_GEN", sequenceName = "BANK_SEQ", initialValue = 1, allocationSize = 50)

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_SEQ_GEN")
    @Column(name = "BANK_ACCOUNT_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User owner;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "AMOUNT")
    private Double amount;


    public boolean checkOwner(User owner) {
        return this.owner.getId().equals(owner.getId());
    }

    public boolean checkAmount(Double amount) {
        return this.amount < amount;
    }

    public void calculateAmount(Double amount) {
        this.amount += amount;
    }

}
