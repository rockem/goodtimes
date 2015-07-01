package test.com.goodtimes.users;

import com.goodtimes.users.AdminUserCreator;
import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.math.BigInteger;

import static org.mockito.Mockito.*;

public class AdminUserCreatorTest {

    private static final String ADMIN_USERNAME = "kuku";

    private static final GoodtimesUser NEW_ADMIN_USER = GoodtimesUser.builder()
            .username(ADMIN_USERNAME)
            .password("1234")
            .email("admin@g.com")
            .role("ADMIN").build();

    private static final GoodtimesUser SAVED_ADMIN_USER = GoodtimesUser.builder()
            .id(BigInteger.valueOf(325))
            .username(ADMIN_USERNAME)
            .password("123456")
            .role("ADMIN").build();

    private final UsersRepository usersRepository = mock(UsersRepository.class);
    private final AdminUserCreator creator = new AdminUserCreator(usersRepository);

    @Before
    public void setUp() throws Exception {
        creator.adminUsername = ADMIN_USERNAME;
        creator.adminPassword = NEW_ADMIN_USER.getPassword();
        creator.adminEmail = NEW_ADMIN_USER.getEmail();
    }

    @Test
    public void shouldSaveNewlyCreatedAdminUserIfNotExists() throws Exception {
        when(usersRepository.findByUsername(ADMIN_USERNAME)).thenReturn(null);
        creator.create();
        verify(usersRepository).save(NEW_ADMIN_USER);
    }

    @Test
    public void updateAdminUserIfAlreadyExists() throws Exception {
        when(usersRepository.findByUsername(ADMIN_USERNAME)).thenReturn(SAVED_ADMIN_USER);
        creator.create();
        verify(usersRepository).save(
                GoodtimesUser.createBuilderFrom(NEW_ADMIN_USER)
                        .id(SAVED_ADMIN_USER.getId())
                        .build());

    }

    @Test
    public void adminShouldBeEnabledUser() throws Exception {
        when(usersRepository.findByUsername(ADMIN_USERNAME)).thenReturn(null);
        creator.create();
        verify(usersRepository).save(argThat(new UserIsEnabled()));

    }

    private class UserIsEnabled extends ArgumentMatcher<GoodtimesUser> {

        @Override
        public boolean matches(Object argument) {
            GoodtimesUser gtu = (GoodtimesUser) argument;
            return gtu.isEnabled() &&
                    gtu.isAccountNonExpired() &&
                    gtu.isAccountNonLocked() &&
                    gtu.isCredentialsNonExpired();
        }
    }
}