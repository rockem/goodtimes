package test.com.goodtimes.controller;

import com.goodtimes.repository.EventsRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public EventsRepository eventsRepository() {
        return Mockito.mock(EventsRepository.class);
    }
}
