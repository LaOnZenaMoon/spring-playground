package me.lozm.api;

import me.lozm.restDocs.ApiDocumentUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static me.lozm.restDocs.ApiDocumentUtils.getDocumentRequest;
import static me.lozm.restDocs.ApiDocumentUtils.getDocumentResponse;
import static me.lozm.restDocs.DocumentFormatGenerator.getHealthCheckType;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void healthCheckGet() throws Exception {
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/healthCheck?format={format}",
                        "full"
                ).accept(MediaType.APPLICATION_JSON)
        );

        //Then
        result.andExpect(status().is(200))
                .andDo(document("get-healthCheck",
                        getDocumentRequest(),
                        getDocumentResponse(),
//                        pathParameters(
//                                parameterWithName("format").description("format").attributes(getHealthCheckType())
//                        ),
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
    void healthCheckPost() {
    }

    @Test
    void healthCheckPut() {
    }

    @Test
    void healthCheckPatch() {
    }

    @Test
    void healthCheckDelete() {
    }
}
