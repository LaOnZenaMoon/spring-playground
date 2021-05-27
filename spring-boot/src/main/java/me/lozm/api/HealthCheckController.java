package me.lozm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RequestMapping("healthCheck")
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

    @GetMapping(produces = "application/json")
    public ResponseEntity<ShortResponseDto> healthCheckGet(@RequestParam(value = "format", required = false) String format) {
        if ("short".equals(format)) {
            return new ResponseEntity<>(new ShortResponseDto(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
        } else if ("full".equals(format)) {
            return new ResponseEntity<>(new FullResponseDto(HttpStatus.OK.getReasonPhrase(), LocalDateTime.now(ZoneOffset.UTC)), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity healthCheckPut() {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity healthCheckPost() {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping(produces = "application/json")
    public ResponseEntity healthCheckDelete() {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
