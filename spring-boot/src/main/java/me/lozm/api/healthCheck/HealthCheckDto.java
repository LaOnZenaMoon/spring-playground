package me.lozm.api.healthCheck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HealthCheckDto {

    @Getter
    public static class ShortResponse {

        private String status;

        public ShortResponse(String status) {
            this.status = status;
        }
    }

    @Getter
    public static class FullResponse extends ShortResponse {

        private String currentTime;

        public FullResponse(String status) {
            super(status);
        }

        public FullResponse(String status, LocalDateTime currentTime) {
            super(status);
            this.currentTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }
    }

    @Getter @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class RequestBody {
        private Long healthCheckId;
    }

}
