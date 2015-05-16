package test.com.goodtimes.config;

import com.goodtimes.config.SecurityConfiguration;
import org.junit.Test;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SecurityConfigurationTest {

    @Test
    public void shouldSomething() throws Exception {
        SecurityConfigurationStub sc = new SecurityConfigurationStub();
        System.out.println("Asdasd");
    }

    private class ObjectPostProcessorStub implements ObjectPostProcessor<Object> {
        @Override
        public <O extends Object> O postProcess(O object) {
            return null;

        }
    }


    private class SecurityConfigurationStub extends SecurityConfiguration {


        public ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessorStub();
        public AuthenticationManagerBuilder authBuilder = new AuthenticationManagerBuilder(objectPostProcessor);
        public Map<Class<Object>, Object> sharedObjects = new HashMap<>();
        public HttpSecurity httpSecurity = new HttpSecurity(objectPostProcessor, authBuilder, sharedObjects);

        public SecurityConfigurationStub() throws Exception {
            configure(httpSecurity);
        }


    }
}