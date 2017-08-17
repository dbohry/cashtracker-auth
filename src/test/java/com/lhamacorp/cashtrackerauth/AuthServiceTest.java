package com.lhamacorp.cashtrackerauth;

import com.lhamacorp.cashtrackerauth.entity.role.Role;
import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.service.AuthService;
import com.lhamacorp.cashtrackerauth.service.PasswordEncoder;
import com.lhamacorp.cashtrackerauth.service.UserService;
import com.lhamacorp.cashtrackerauth.validator.AuthValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.matchers.Any;
import org.mockito.internal.matchers.AnyVararg;

import javax.servlet.ServletException;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    public static final String MSG_ERROR_NOT_NULL = "User and password must be not null.";

    private AuthService service;
    private UserService userService;
    private PasswordEncoder encoder;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        encoder = new PasswordEncoder();
        service = new AuthService(userService, new AuthValidator(), encoder);
    }

    @Test
    public void deveGerarTokenQuandoTudoOk() throws ServletException {
        User saved = new User();
        saved.setId(1L);
        saved.setEnabled(Boolean.TRUE);
        saved.setUsername("username");
        saved.setPassword(encoder.encode("password"));
        saved.setRoles(Collections.singletonList(new Role(1L, "ROLE_USER")));

        when(userService.findByLogin(anyString(), anyString()))
                .thenReturn(saved);

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        service.getToken(user);
    }

    @Test
    public void deveVerificarSeUsuarioExiste() throws ServletException {
        User user = new User();
        user.setUsername("Graciela");
        user.setPassword("gra");

        when(userService.findByLogin("Graciela", "gra"))
                .thenReturn(null);
        try {
            service.getToken(user);
            expectedException.expect(ServletException.class);
        } catch (ServletException e) {
            assertThat(e.getMessage(), is("User not found."));
        }
    }

    @Test
    public void deveVerificarQuandoNulo() {
        try {
            service.getToken(null);
            expectedException.expect(ServletException.class);
        } catch (ServletException e) {
            assertThat(e.getMessage(), is(MSG_ERROR_NOT_NULL));
        }
    }

    @Test
    public void deveVerificarQuandoNew() {
        try {
            service.getToken(new User());
            expectedException.expect(ServletException.class);
        } catch (ServletException e) {
            assertThat(e.getMessage(), is(MSG_ERROR_NOT_NULL));
        }
    }

    @Test
    public void deveVerificarQuandoSemPassword() {
        User user = new User();
        user.setUsername("username");

        try {
            service.getToken(user);
            expectedException.expect(ServletException.class);
        } catch (ServletException e) {
            assertThat(e.getMessage(), is(MSG_ERROR_NOT_NULL));
        }
    }

    @Test
    public void deveVerificarQuandoSemUsername() {
        User user = new User();
        user.setPassword("password");

        try {
            service.getToken(user);
            expectedException.expect(ServletException.class);
        } catch (ServletException e) {
            assertThat(e.getMessage(), is(MSG_ERROR_NOT_NULL));
        }
    }

}
