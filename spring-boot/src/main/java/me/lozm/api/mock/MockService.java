package me.lozm.api.mock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MockService {

    private final ExternalApiService externalApiService;


    public void testExternalApiIntegrationV1(MockDto.ExternalApiIntegrationV1 requestDto) {
        getInternalData();
        setInternalData();

        externalApiService.getExternalData();
        externalApiService.setExternalData();
    }

    public void testExternalApiIntegrationV2(MockDto.ExternalApiIntegrationV1 requestDto) {
        getInternalData();
        setInternalData();
        getExternalData();
        setExternalData();
    }

    public List<String> getExternalData() {
        throw new RestClientException("Failed to invoke GET External API");
    }

    public String setExternalData() {
        throw new RestClientException("Failed to invoke POST External API");
    }

    private void getInternalData() {
        log.info("Succeed to get Internal Data");
    }

    private void setInternalData() {
        log.info("Succeed to set Internal Data");
    }

}
