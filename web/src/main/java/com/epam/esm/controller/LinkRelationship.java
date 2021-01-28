package com.epam.esm.controller;

public interface LinkRelationship<T> {

    void addDependenciesLinks(T entityDto);
}
