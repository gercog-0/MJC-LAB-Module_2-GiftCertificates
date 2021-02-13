package com.epam.esm.service.api.dto;

import java.util.Objects;

public class RoleDto {

    private Long id;
    private String name;

    public RoleDto() {
    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id) &&
                Objects.equals(name, roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}