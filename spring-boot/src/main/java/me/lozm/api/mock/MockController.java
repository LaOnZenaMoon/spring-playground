package me.lozm.api.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("mock")
@RestController
@RequiredArgsConstructor
public class MockController {

    private final MockService mockService;


    @PostMapping("external-api-integration/v1")
    public ResponseEntity testExternalApiIntegrationV1(@RequestBody @Validated MockDto.ExternalApiIntegrationV1 requestDto) {
        mockService.testExternalApiIntegrationV1(requestDto);
        return new ResponseEntity<>(new MockDto.Response(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

    @PostMapping("external-api-integration/v2")
    public ResponseEntity testExternalApiIntegrationV2(@RequestBody @Validated MockDto.ExternalApiIntegrationV1 requestDto) {
        mockService.testExternalApiIntegrationV2(requestDto);
        return new ResponseEntity<>(new MockDto.Response(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

}
