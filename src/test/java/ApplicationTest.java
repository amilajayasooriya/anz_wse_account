import com.anz.wse.account.AccountApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AccountApplication.class)
@ActiveProfiles("test")
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }
}
