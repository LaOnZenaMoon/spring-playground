package me.lozm.api.async;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncTestController {

    private final AsyncTestService asyncService;


    @PostMapping("/v1/async")
    public ResponseEntity<AsyncDto.Response> testAsynchronousApiVersion1(@RequestBody AsyncDto.Request requestDto) {
        String result = asyncService.testSynchronousLogic(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AsyncDto.Response(result));
    }

    @PostMapping("/v2/async")
    public ResponseEntity<AsyncDto.Response> testAsynchronousApiVersion2(@RequestBody AsyncDto.Request requestDto) {
        String result = asyncService.testAsynchronousLogic(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AsyncDto.Response(result));
    }

    @PostMapping("/v3/async")
    public ResponseEntity<AsyncDto.Response> testAsynchronousApiVersion3(@RequestBody AsyncDto.Request requestDto) {
        String result = asyncService.testSynchronousAndCompletableFutureLogic(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AsyncDto.Response(result));
    }

}
