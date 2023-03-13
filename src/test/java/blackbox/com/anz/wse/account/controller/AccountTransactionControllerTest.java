package blackbox.com.anz.wse.account.controller;

import blackbox.com.anz.wse.account.controller.utills.LogUtils;
import blackbox.com.anz.wse.account.controller.utills.PageUtils;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.anz.wse.account.controller.AccountTransactionController;
import com.anz.wse.account.dto.AccountTransactionDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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
public class AccountTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ListAppender<ILoggingEvent> logAppender;

    @Test
    public void get_account_transaction_success() throws Exception {
        final String accountNumber = "100309209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        param("page", "0").
                        param("size", "10").
                        param("sortBy", "valueDate").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = PageUtils.getContentString(pageString);
        List<AccountTransactionDTO> accountTransactionDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertFalse(accountTransactionDTOList.isEmpty());
        Assertions.assertEquals(3, accountTransactionDTOList.size());
    }

    @Test
    public void get_account_transaction_success_page_size_one() throws Exception {
        final String accountNumber = "100309209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        param("page", "0").
                        param("size", "1").
                        param("sortBy", "valueDate").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = PageUtils.getContentString(pageString);
        List<AccountTransactionDTO> accountTransactionDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertFalse(accountTransactionDTOList.isEmpty());
        Assertions.assertEquals(1, accountTransactionDTOList.size());
    }

    @Test
    public void get_account_transaction_success_page_size_one_sort_by_transaction_narrative() throws Exception {
        final String accountNumber = "100309209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        param("page", "0").
                        param("size", "1").
                        param("sortBy", "transactionNarrative").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = PageUtils.getContentString(pageString);
        List<AccountTransactionDTO> accountTransactionDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertFalse(accountTransactionDTOList.isEmpty());
        Assertions.assertEquals(1, accountTransactionDTOList.size());
        Assertions.assertEquals("ATM withdrawal", accountTransactionDTOList.get(0).getTransactionNarrative());
    }

    @Test
    public void get_account_transaction_for_no_transaction_account() throws Exception {
        final String accountNumber = "100209209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        param("page", "0").
                        param("size", "10").
                        param("sortBy", "valueDate").
                        header("x-authToken", "jskdjebsjshepss:100").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = PageUtils.getContentString(pageString);
        List<AccountTransactionDTO> accountTransactionDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertTrue(accountTransactionDTOList.isEmpty());
    }

    @Test
    public void get_account_transaction_account_number_validation_failed() throws Exception {
        final String accountNumber = "100111200066619";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        param("page", "0").
                        param("size", "10").
                        param("sortBy", "valueDate").
                        header("x-authToken", "jskdjebsjshepss:200").
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertTrue(pageString.contains("Invalid account number format"));
    }

    @Test
    public void get_account_transaction_account_number_no_missing_correlation_id_header() throws Exception {
        final String accountNumber = "100209209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        header("x-authToken", "jskdjebsjshepss:200")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertTrue(pageString.contains("Required header 'x-correlationId' is not present."));
    }

    @Test
    public void get_account_transaction_account_number_no_missing_auth_header() throws Exception {
        final String accountNumber = "100209209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        header("x-correlationId", "test_correlation_1")).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        Assertions.assertTrue(pageString.contains("Required header 'x-authToken' is not present."));
    }

    @ParameterizedTest
    @CsvSource(value = {"jskdjebsjshepss| message=\"Authentication token mismatch\", error=\"ERROR 5145\"|Authentication token mismatch, ERROR 5145",
            "jskdjebsj:fhdd,2| message=\"Authentication token mismatch\", error=\"ERROR 5632\"| Authentication token mismatch, ERROR 5632"}, delimiter = '|')
    public void get_accounts_no_accounts_incorrect_formatted_user_id_user_token(String token, String errorLog, String returnError) throws Exception {
        logAppender = LogUtils.setLogLevel((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME), new ListAppender<>());

        final String accountNumber = "100209209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
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

    @ParameterizedTest
    @ValueSource(strings = {"testCorrelation_id_1", "testCorrelation_id_1"})
    public void get_accounts_validate_logs_contains_correlation_id(String correlationId) throws Exception {
        logAppender = LogUtils.setLogLevel((Logger) LoggerFactory.getLogger(AccountTransactionController.class), new ListAppender<>());

        final String accountNumber = "100209209";
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/account-transactions/{accountNumber}", accountNumber).
                        header("x-authToken", "jskdjebsjshepss:67").
                        header("x-correlationId", correlationId)).
                andDo(print()).
                andExpect(status().isOk()).
                andReturn();

        String pageString = result.getResponse().getContentAsString();
        String contentString = PageUtils.getContentString(pageString);

        List<AccountTransactionDTO> accountTransactionDTOList = objectMapper.readValue(contentString, new TypeReference<>() {
        });

        Assertions.assertTrue(accountTransactionDTOList.isEmpty());

        List<ILoggingEvent> loggingEvents = logAppender.list;
        Assertions.assertTrue(loggingEvents.stream().anyMatch(iLoggingEvent -> iLoggingEvent.getMDCPropertyMap().getOrDefault("correlationId", "").contains(correlationId)));
        LogUtils.removeAppender((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME), logAppender);
    }
}
