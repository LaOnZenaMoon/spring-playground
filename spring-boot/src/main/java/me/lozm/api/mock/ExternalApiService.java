package me.lozm.api.mock;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class ExternalApiService {

    public List<String> getExternalData() {
        throw new RestClientException("Failed to invoke GET External API");
    }

    public String setExternalData() {
        throw new RestClientException("Failed to invoke POST External API");
    }

}
