//package me.lozm.api.transaction;
//
//import lombok.RequiredArgsConstructor;
//import me.lozm.domain.bank.entity.BankAccount;
//import me.lozm.domain.bank.entity.TransferHistory;
//import me.lozm.domain.bank.repository.TransferHistoryRepository;
//import me.lozm.domain.bank.service.BankAccountHelperService;
//import me.lozm.domain.bank.vo.BankTransferVo;
//import me.lozm.domain.user.entity.User;
//import me.lozm.domain.user.service.UserHelperService;
//import me.lozm.global.code.UseYn;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class OuterService {
//
//    private final NestedInnerService nestedInnerService;
//    private final TransferHistoryRepository transferHistoryRepository;
//    private final UserHelperService userHelperService;
//    private final BankAccountHelperService bankAccountHelperService;
//
//
//    public void transfer(BankTransferVo requestVo) {
//        final User sendUser = userHelperService.getUser(requestVo.getSendUserId(), UseYn.USE);
//        if (!sendUser.checkPassword(requestVo.getSendUserPassword())) {
//            throw new IllegalArgumentException("사용자 비밀번호가 잘못되었습니다.");
//        }
//
//        final BankAccount sendUserBankAccount = bankAccountHelperService.getBankAccount(requestVo.getSendUserBankAccountId());
//        if (!sendUserBankAccount.checkOwner(sendUser)) {
//            throw new IllegalArgumentException("송신자 계정과 계좌번호 정보가 일치하지 않습니다.");
//        }
//
//        final Double transferAmount = requestVo.getTransferAmount();
//        if (!sendUserBankAccount.checkAmount(transferAmount)) {
//            throw new IllegalArgumentException("송신할 금액이 계좌 금액을 초과하였습니다.");
//        }
//        sendUserBankAccount.calculateAmount(-transferAmount);
//
//        //TODO 수신자 계정 정보 조회 및 금액 이체 처리
//        // 1. API 로 전환
//        // 2. Nested Transaction 처리
//        // 3. UncheckedException No Rollback 관련 테스트
//        final BankAccount receiveUserBankAccount = bankAccountHelperService.getBankAccount(requestVo.getReceiveUserBankAccountId());
//        receiveUserBankAccount.calculateAmount(transferAmount);
//
//        transferHistoryRepository.save(new TransferHistory(sendUserBankAccount, receiveUserBankAccount, transferAmount));
//    }
//
//}
