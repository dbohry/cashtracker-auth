package com.lhamacorp.cashtrackerauth;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.entity.user.UserConverter;
import com.lhamacorp.cashtrackerauth.entity.user.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class UserConverterTest {

    private static final String EMAIL = "email";
    private static final Long ID = 1L;
    private static final String PASSWORD = "pass";
    private static final String USERNAME = "user";

    private UserConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new UserConverter();
    }

    @Test
    public void deveValidarQuandoNulo() {
        assertThat(converter.convert((User) null), is(nullValue()));
        assertThat(converter.convert((UserDTO) null), is(nullValue()));
    }

    @Test
    public void deveValidarQuandoNew() {
        assertThat(converter.convert(new User()), is(new UserDTO()));
        assertThat(converter.convert(new UserDTO()), is(new User()));
    }

    @Test
    public void deveConverterParaDTO() {
        assertThat(converter.convert(buildBO()), is(buildDTO()));
    }

    @Test
    public void deveConverterParaBO() {
        assertThat(converter.convert(buildDTO()), is(buildBO()));
    }

    private UserDTO buildDTO() {
        UserDTO dto = new UserDTO();

        dto.setEmail(EMAIL);
        dto.setId(ID);
        dto.setPassword(PASSWORD);
        dto.setUsername(USERNAME);

        return dto;
    }

    private User buildBO() {
        User bo = new User();

        bo.setEmail(EMAIL);
        bo.setId(ID);
        bo.setPassword(PASSWORD);
        bo.setUsername(USERNAME);

        return bo;
    }

}
