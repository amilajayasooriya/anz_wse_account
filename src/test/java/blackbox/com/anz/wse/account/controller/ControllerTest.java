package blackbox.com.anz.wse.account.controller;

import com.anz.wse.account.AccountApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest(classes = AccountApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql({"/db/cleanup.sql", "/db/account_and_transaction_data.sql"})
public @interface ControllerTest {
}
