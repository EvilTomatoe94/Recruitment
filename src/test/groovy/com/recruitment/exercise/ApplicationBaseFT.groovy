package com.recruitment.exercise

import com.fasterxml.jackson.databind.ObjectMapper

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD

@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(classes = TestContext.class)
@ActiveProfiles("tests")
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = [
        "classpath:/db/test-cleanup.sql"
])
class ApplicationBaseFT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper
}
