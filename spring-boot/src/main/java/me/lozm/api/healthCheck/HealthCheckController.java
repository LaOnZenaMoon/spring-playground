package me.lozm.api.healthCheck;

import me.lozm.global.annotation.CustomAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequestMapping(value = "healthCheck", produces = "application/json")
@RestController
public class HealthCheckController {

    @GetMapping
    @CustomAnnotation(name = "test", values = {"first", "second"})
    public ResponseEntity<HealthCheckDto.ShortResponse> getHealthCheck(@RequestParam(value = "format", required = false) String format) {
        if ("short".equals(format)) {
            return new ResponseEntity<>(new HealthCheckDto.ShortResponse(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
        } else if ("full".equals(format)) {
            return new ResponseEntity<>(new HealthCheckDto.FullResponse(HttpStatus.OK.getReasonPhrase(), LocalDateTime.now(ZoneOffset.UTC)), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    class PostBody {

    }

    @PostMapping
    public ResponseEntity postHealthCheck(@RequestBody HealthCheckDto.RequestBody requestDto) {
        return new ResponseEntity<>(new HealthCheckDto.ShortResponse(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putHealthCheck(@RequestBody HealthCheckDto.RequestBody requestDto) {
        return new ResponseEntity<>(new HealthCheckDto.ShortResponse(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity healthCheckPatch(@RequestBody HealthCheckDto.RequestBody requestDto) {
        return new ResponseEntity<>(new HealthCheckDto.ShortResponse(HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{healthCheckId}")
    public ResponseEntity healthCheckDelete(@PathVariable(value = "healthCheckId") Long healthCheckId) {
        return new ResponseEntity<>(new HealthCheckDto.ShortResponse(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

}
