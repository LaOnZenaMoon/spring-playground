package me.lozm.api.healthCheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static me.lozm.restDocs.ApiDocumentUtils.getDocumentRequest;
import static me.lozm.restDocs.ApiDocumentUtils.getDocumentResponse;
import static me.lozm.restDocs.DocumentFormatGenerator.getHealthCheckType;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void healthCheckGet() throws Exception {
        // Given & When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/healthCheck?format={format}",
                        "full"
                ).accept(MediaType.APPLICATION_JSON)
        );

        // Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(document("get-healthCheck",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                          parameterWithName("format").description("포맷").attributes(getHealthCheckType())
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"),
                                fieldWithPath("currentTime").type(JsonFieldType.STRING).description("현재 시간(YYYY-MM-DD'T'HH:mm:ss'Z')").optional()
                        )
                ));
    }

    @Test
    void healthCheckPost() throws Exception {
        // Given
        HealthCheckDto.RequestBody requestDto = HealthCheckDto.RequestBody.builder()
                .healthCheckId(1L)
                .build();

        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/healthCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
        );

        // Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(document("post-healthCheck",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(fieldWithPath("healthCheckId").type(JsonFieldType.NUMBER).description("Health Check ID")),
                        responseFields(fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"))
                ));
    }

    @Test
    void healthCheckPut() throws Exception {
        // Given
        HealthCheckDto.RequestBody requestDto = HealthCheckDto.RequestBody.builder()
                .healthCheckId(1L)
                .build();

        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.put("/healthCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
        );

        // Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(document("put-healthCheck",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(fieldWithPath("healthCheckId").type(JsonFieldType.NUMBER).description("Health Check ID")),
                        responseFields(fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"))
                ));
    }

    @Test
    void healthCheckPatch() throws Exception {
        // Given
        HealthCheckDto.RequestBody requestDto = HealthCheckDto.RequestBody.builder()
                .healthCheckId(1L)
                .build();

        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.patch("/healthCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
        );

        // Then
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(document("patch-healthCheck",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(fieldWithPath("healthCheckId").type(JsonFieldType.NUMBER).description("Health Check ID")),
                        responseFields(fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"))
                ));
    }

    @Test
    void healthCheckDelete() throws Exception {
        // Given
        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.delete("/healthCheck/{healthCheckId}",
                        1L
                ).accept(MediaType.APPLICATION_JSON)
        );

        // Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(document("delete-healthCheck",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("healthCheckId").description("HealthCheckId")),
                        responseFields(fieldWithPath("status").type(JsonFieldType.STRING).description("HTTP 상태"))
                ));
    }

}
