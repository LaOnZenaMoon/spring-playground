package me.lozm.domain.bank.vo;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
public class BankTransferVo {

    private Long sendUserBankAccountId;
    private Long sendUserId;
    private String sendUserPassword;

    private Double transferAmount;

    private Long receiveUserBankAccountId;


    public BankTransferVo(Long sendUserBankAccountId, Long sendUserId, String sendUserPassword, Double transferAmount, Long receiveUserBankAccountId) {
        checkArgument(isNotEmpty(sendUserBankAccountId));
        checkArgument(isNotEmpty(sendUserId));
        checkArgument(isNotBlank(sendUserPassword));
        checkArgument(isNotEmpty(transferAmount) && transferAmount > 0);
        checkArgument(isNotEmpty(receiveUserBankAccountId));

        this.sendUserBankAccountId = sendUserBankAccountId;
        this.sendUserId = sendUserId;
        this.sendUserPassword = sendUserPassword;
        this.transferAmount = transferAmount;
        this.receiveUserBankAccountId = receiveUserBankAccountId;
    }
}
