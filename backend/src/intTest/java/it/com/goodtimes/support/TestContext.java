package it.com.goodtimes.support;

import com.goodtimes.events.EventsRepository;
import com.goodtimes.users.UsersRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public EventsRepository eventsRepository() {
        return Mockito.mock(EventsRepository.class);
    }

    @Bean
    public UsersRepository usersRepository() {
        return Mockito.mock(UsersRepository.class);
    }

}