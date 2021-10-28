package me.lozm.global.code;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TransferStatus {

    PENDING("PENDING", "전체"),
    SUCCESS("SUCCESS", "성공"),
    FAIL("FAIL", "실패"),
    ;

    private String code;
    private String description;

    TransferStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TransferStatus findCode(String code) {
        return Arrays.stream(TransferStatus.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

}
