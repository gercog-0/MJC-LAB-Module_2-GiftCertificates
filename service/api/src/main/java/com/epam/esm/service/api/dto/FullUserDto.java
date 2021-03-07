package com.epam.esm.service.api.dto;


import java.util.List;
import java.util.Objects;

public class FullUserDto extends UserDto {

    private String password;
    private List<RoleDto> roles;

    public FullUserDto() {
    }

    public FullUserDto(Long id, String name, String login, String password, List<RoleDto> roles) {
        super(id, name, login);
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
