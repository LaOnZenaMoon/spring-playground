package me.lozm.api.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static me.lozm.restDocs.ApiDocumentUtils.getDocumentRequest;
import static me.lozm.restDocs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExternalApiService externalApiServiceMock;

    @SpyBean
    private MockService mockService;


    @Test
    void testExternalApiIntegrationV1() throws Exception {
        // Given
        MockDto.ExternalApiIntegrationV1 requestDto = MockDto.ExternalApiIntegrationV1.builder()
                .mockId(1L)
                .build();

        Mockito.when(externalApiServiceMock.getExternalData()).thenReturn(Arrays.asList("one", "two", "three"));
        Mockito.when(externalApiServiceMock.setExternalData()).thenReturn("success");

        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/mock/external-api-integration/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
        );

        // Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(document("post-external-api-integration-v1",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(fieldWithPath("mockId").type(JsonFieldType.NUMBER).description("Mock ID")),
                        responseFields(fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"))
                ));
    }

    @Test
    void testExternalApiIntegrationV2() throws Exception {
        // Given
        MockDto.ExternalApiIntegrationV1 requestDto = MockDto.ExternalApiIntegrationV1.builder()
                .mockId(1L)
                .build();

        Mockito.doAnswer(invocation -> {
            log.info("Succeed to invoke GET External API");
            return null;
        }).when(mockService).getExternalData();

        Mockito.doAnswer(invocation -> {
            log.info("Succeed to invoke POST External API");
            return null;
        }).when(mockService).setExternalData();

        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/mock/external-api-integration/v2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
        );

        // Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(document("post-external-api-integration-v2",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(fieldWithPath("mockId").type(JsonFieldType.NUMBER).description("Mock ID")),
                        responseFields(fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"))
                ));
    }

}
