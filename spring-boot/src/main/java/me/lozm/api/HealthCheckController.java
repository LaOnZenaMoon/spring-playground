package me.lozm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RequestMapping(value = "healthCheck", produces = "application/json")
@RestController
public class HealthCheckController {

    class ShortResponseDto {
        String status;

        public ShortResponseDto(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    class FullResponseDto extends ShortResponseDto {
        String currentTime;

        public FullResponseDto(String status) {
            super(status);
        }

        public FullResponseDto(String status, LocalDateTime currentTime) {
            super(status);
            this.currentTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }
    }

    @GetMapping
    public ResponseEntity<ShortResponseDto> getHealthCheck(@RequestParam(value = "format", required = false) String format) {
        if ("short".equals(format)) {
            return new ResponseEntity<>(new ShortResponseDto(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
        } else if ("full".equals(format)) {
            return new ResponseEntity<>(new FullResponseDto(HttpStatus.OK.getReasonPhrase(), LocalDateTime.now(ZoneOffset.UTC)), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity postHealthCheck() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putHealthCheck() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity healthCheckPatch() {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity healthCheckDelete() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
