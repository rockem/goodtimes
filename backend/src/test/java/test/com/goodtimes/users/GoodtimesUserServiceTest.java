package test.com.goodtimes.users;

import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.GoodtimesUserService;
import com.goodtimes.users.UsersRepository;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigInteger;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoodtimesUserServiceTest {

    private final UsersRepository usersRepository = mock(UsersRepository.class);
    private final GoodtimesUserService service = new GoodtimesUserService(usersRepository);

    public static final GoodtimesUser POPOV = new GoodtimesUser(
            BigInteger.valueOf(34), "popov", "1234", "p.p@p.com");

    @Test
    public void shouldRetrieveUser() throws Exception {
        when(usersRepository.findByUsername(POPOV.getUsername())).thenReturn(POPOV);
        UserDetails user = service.loadUserByUsername(POPOV.getUsername());
        assertEquals(POPOV.getUsername(), user.getUsername());
        assertEquals(POPOV.getPassword(), user.getPassword());
    }

    @Test
    public void shouldRetrieveAdminUser() throws Exception {
        UserDetails user = service.loadUserByUsername(GoodtimesUserService.ADMIN_USERNAME);
        assertEquals(GoodtimesUserService.ADMIN_USERNAME, user.getUsername());
        assertEquals(GoodtimesUserService.ADMIN_PASSWORD, user.getPassword());
    }


    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUserNotFoundWhenUsernameNotExists() throws Exception {
        when(usersRepository.findByUsername(any())).thenReturn(null);
        service.loadUserByUsername(POPOV.getUsername());
    }
}