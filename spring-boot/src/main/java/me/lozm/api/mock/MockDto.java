package me.lozm.api.mock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MockDto {

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class ExternalApiIntegrationV1 {
        private Long mockId;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private String status;
    }

}
