package blackbox.com.anz.wse.account.controller;

import blackbox.com.anz.wse.account.controller.utills.LogUtils;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.model.Currency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ListAppender<ILoggingEvent> logAppender;

    @Test
    public void get_accounts_success() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        param("page", "0").
                        param("size", "10").
                        param("sortBy", "currency").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = getContentPart(pageString);
        List<AccountDTO> accountDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertFalse(accountDTOList.isEmpty());
        Assertions.assertEquals(2, accountDTOList.size());
    }

    @Test
    public void get_accounts_success_but_empty_response_next_page() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        param("page", "1").
                        param("size", "10").
                        param("sortBy", "currency").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = getContentPart(pageString);
        List<AccountDTO> accountDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertTrue(accountDTOList.isEmpty());
    }

    @Test
    public void get_accounts_success_second_account_page_size_one_sort_by_currency() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        param("page", "1").
                        param("size", "1").
                        param("sortBy", "currency").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = getContentPart(pageString);
        List<AccountDTO> accountDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertFalse(accountDTOList.isEmpty());
        Assertions.assertEquals(1, accountDTOList.size());
        Assertions.assertEquals(Currency.USD, accountDTOList.get(0).getCurrency());
    }

    @Test
    public void get_accounts_no_accounts_invalid_user_token() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        header("x-authToken", "jskdjebsjshepss:67").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = getContentPart(pageString);
        List<AccountDTO> accountDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });
        Assertions.assertTrue(accountDTOList.isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"testCorrelation_id_1", "testCorrelation_id_1"})
    public void get_accounts_validate_logs_contains_correlation_id(String correlationId) throws Exception {
        logAppender = LogUtils.setLogLevel((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME), new ListAppender<>());

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        header("x-authToken", "jskdjebsjshepss:67").
                        header("x-correlationId", correlationId)).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = getContentPart(pageString);
        List<AccountDTO> accountDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });
        Assertions.assertTrue(accountDTOList.isEmpty());

        List<ILoggingEvent> loggingEvents = logAppender.list;
        Assertions.assertTrue(loggingEvents.stream().anyMatch(iLoggingEvent -> iLoggingEvent.getFormattedMessage().contains(correlationId)));
        LogUtils.removeAppender((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME), logAppender);
    }

    @ParameterizedTest
    @CsvSource(value = {"jskdjebsjshepss| message=\"Authentication token mismatch\", error=\"ERROR 5145\"|Authentication token mismatch, ERROR 5145",
            "jskdjebsj:fhdd,2| message=\"Authentication token mismatch\", error=\"ERROR 5632\"| Authentication token mismatch, ERROR 5632"}, delimiter = '|')
    public void get_accounts_no_accounts_incorrect_formatted_user_id_user_token(String token, String errorLog, String returnError) throws Exception {
        logAppender = LogUtils.setLogLevel((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME), new ListAppender<>());

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        header("x-authToken", token).
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertEquals(returnError, pageString);
        List<ILoggingEvent> loggingEvents = logAppender.list;

        Assertions.assertTrue(loggingEvents.stream().anyMatch(iLoggingEvent -> iLoggingEvent.getFormattedMessage().contains(errorLog)));
        LogUtils.removeAppender((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME), logAppender);
    }

    @Test
    public void get_accounts_no_missing_auth_header() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertTrue(pageString.contains("Required header 'x-authToken' is not present."));
    }

    @Test
    public void get_accounts_no_missing_correlation_id_header() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts?page=0&size=10&sortBy=id").
                        header("x-authToken", "jskdjebsjshepss:67")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertTrue(pageString.contains("Required header 'x-correlationId' is not present."));
    }

    @Test
    public void get_single_account_success() throws Exception {
        final String accountNumber = "200066619";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts/{accountNumber}", accountNumber).
                        header("x-authToken", "jskdjebsjshepss:200").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        AccountDTO accountDTO = objectMapper.readValue(result.getResponse().getContentAsString(), AccountDTO.class);

        Assertions.assertNotNull(accountDTO);
        Assertions.assertEquals(accountNumber, accountDTO.getAccountNumber());
    }

    @Test
    public void get_single_account_user_id_account_number_mismatch() throws Exception {
        final String accountNumber = "200066619";
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts/{accountNumber}", accountNumber).
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isNotFound());
    }

    @Test
    public void get_single_account_account_number_validation_failed() throws Exception {
        final String accountNumber = "100111200066619";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts/{accountNumber}", accountNumber).
                        header("x-authToken", "jskdjebsjshepss:200").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertTrue(pageString.contains("Invalid account number format"));
    }

    private String getContentPart(String pageString) {
        return pageString.substring(pageString.indexOf("\"content\":") + "\"content\":".length(), pageString.indexOf(",\"pageable\":"));
    }
}
