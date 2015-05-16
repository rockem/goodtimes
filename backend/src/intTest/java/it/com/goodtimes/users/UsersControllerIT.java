package it.com.goodtimes.users;

import com.goodtimes.users.User;
import com.goodtimes.users.UsersRepository;
import it.com.goodtimes.support.BaseIT;
import it.com.goodtimes.support.MockMvcHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsersControllerIT extends BaseIT {

    public static final String API_USERS = "/api/users";

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void shouldDelegateUserDetailsToRepository() throws Exception {
        User user = new User("USER", "PASS", "MAIL");
        new MockMvcHelper(mockMvc)
                .postObjectToUrl(user, API_USERS)
                .andExpect(status().isCreated());
        verify(usersRepository).save(user);

    }
}