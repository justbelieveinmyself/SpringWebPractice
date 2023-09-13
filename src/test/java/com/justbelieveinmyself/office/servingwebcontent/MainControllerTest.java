package com.justbelieveinmyself.office.servingwebcontent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@WithUserDetails("user")
@Sql(scripts = {"/create-user-before.sql", "/create-messages-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/create-messages-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void mainPageTest() throws Exception{
        mockMvc.perform(get("/main"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//*[@id='navbarSupportedContent']/div[1]/strong")
                        .string("user"));
    }
    @Test
    public void listMessagesTest() throws Exception{
        mockMvc.perform(get("/main"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//*[@id=\"message-list\"]/div").nodeCount(5));
    }
    @Test
    public void addMessageTest() throws Exception{
        String testMessage = "my new message for test";
        String testTag = "blabla";

        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/main")
                .file("file", "123".getBytes())
                .param("text", testMessage)
                .param("tag", testTag);
        mockMvc.perform(multipart.with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(6))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div[1]/strong").string(testMessage))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div[1]/br/span").string("#" + testTag));

    }
    @Test
    public void filterByTagTest() throws Exception{
        String tag = "my tag for test";

        mockMvc.perform(MockMvcRequestBuilders.get("/main")
                        .param("filter", tag))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(2));
    }
}
