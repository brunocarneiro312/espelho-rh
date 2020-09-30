package br.com.imasoft.espelhorh.controller;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ResourceControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("classpath:application.properties")
    private Resource applicationProperties;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Disabled
    @Test
    @DisplayName("Given file, when upload, then return file name")
    public void givenFile_whenUpload_thenReturnFileName() throws Exception {

        // given
        File file = new File(applicationProperties.getURI());

        // then then
        mockMvc.perform(post("/api/v1/resource/upload")
                .content(FileUtils.readFileToByteArray(file))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }
}