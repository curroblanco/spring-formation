package es.urjc.code.service;

import es.urjc.code.entity.AppUser;
import es.urjc.code.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsService userDetailsService = new UserDetailsServiceImpl(userRepository);

    @Test
    public void shouldReturnUser() {
        when(userRepository.findByUsername(any())).thenReturn(dumpAppUser());

        UserDetails user = userDetailsService.loadUserByUsername("TESTUSER");

        assertEquals("TESTUSER", user.getUsername());
        assertEquals("TESTPASSWORD", user.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldReturnException() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        userDetailsService.loadUserByUsername("TESTUSER");
    }

    private AppUser dumpAppUser() {

        return AppUser.builder().username("TESTUSER").password("TESTPASSWORD").build();
    }
}