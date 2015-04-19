package features;

import com.goodtimes.Application;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(Cucumber.class)
//@CucumberOptions(tags="@wip")
public class RunFeatures {

    public static final int SECOND = 1000;
    private static final int mongoPort = 27018;

    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static ConfigurableApplicationContext backend;
    private static Process process;

    @BeforeClass
    public static void startBackend() {
        createDirsForMongoDB();
        executor.submit(() -> {
            try {
                runProcessFor("mongod",
                        "--config", "mongodb.yml",
                        "--port", String.valueOf(mongoPort));
            } catch (Exception e) {
                System.exit(1);
            }
        });
        try {
            Thread.sleep(5 * SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backend = SpringApplication.run(Application.class);
    }

    private static void createDirsForMongoDB() {
        try {
            new File("data").mkdir();
            new File("log").mkdir();
        }catch(Exception e) {
            // Swallow
        }
    }

    private static void runProcessFor(String... args) throws IOException {
        process = new ProcessBuilder(args).start();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
        String line;
        Boolean error = false;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            error = true;
        }
        if(error) {
            throw new ProcessInvocationException();
        }
    }

    @AfterClass
    public static void stopBackend() throws Exception {
        backend.close();
        process.destroy();
        executor.shutdown();
    }

    private static class ProcessInvocationException extends RuntimeException {
    }
}
