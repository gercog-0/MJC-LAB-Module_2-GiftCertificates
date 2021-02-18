package com.epam.esm.service.api.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class UserDto extends RepresentationModel<UserDto> {

    private Long id;
    private String name;
    private String login;

    public UserDto() {
    }

    public UserDto(Long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(login, userDto.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login);
    }
}
