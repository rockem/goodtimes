package it.com.goodtimes.users;

import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.GoodtimesUserService;
import com.goodtimes.users.UsersRepository;
import com.sun.org.apache.bcel.internal.generic.POP;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoodtimesUserServiceTest {

    private final UsersRepository usersRepository = mock(UsersRepository.class);
    private final GoodtimesUserService service = new GoodtimesUserService(usersRepository);

    public static final GoodtimesUser POPOV = GoodtimesUser.builder()
            .id(BigInteger.valueOf(34))
            .username("popov")
            .password("1234")
            .email("p.p@p.com")
            .roles(Arrays.asList("ADMIN", "USER"))
            .build();

    @Test
    public void shouldRetrieveUser() throws Exception {
        when(usersRepository.findByUsername(POPOV.getUsername())).thenReturn(POPOV);
        UserDetails user = service.loadUserByUsername(POPOV.getUsername());
        assertThat(user.getUsername(), is(POPOV.getUsername()));
        assertThat(user.getPassword(), is(POPOV.getPassword()));
        assertThat(((Collection< GrantedAuthority>)user.getAuthorities()), IsCollectionContaining.hasItems(
                new SimpleGrantedAuthority(POPOV.getRoles().get(0)),
                new SimpleGrantedAuthority(POPOV.getRoles().get(1))
        ));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUserNotFoundWhenUsernameNotExists() throws Exception {
        when(usersRepository.findByUsername(any())).thenReturn(null);
        service.loadUserByUsername(POPOV.getUsername());
    }
}