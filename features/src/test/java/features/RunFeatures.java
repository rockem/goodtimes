package features;

import com.goodtimes.Application;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(Cucumber.class)
public class RunFeatures {

    static ConfigurableApplicationContext backend;

    @BeforeClass
    public static void startBackend() {
        backend = SpringApplication.run(Application.class);
    }

    @AfterClass
    public static void stopBackend() throws Exception {
        backend.close();

    }
}
