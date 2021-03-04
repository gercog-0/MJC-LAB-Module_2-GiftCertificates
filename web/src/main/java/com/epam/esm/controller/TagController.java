package com.epam.esm.controller;

import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<TagDto> findAllTags(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        List<TagDto> tagsDto = tagService.findAll(paginationDto);
        tagsDto.forEach(this::addDependenciesLinks);
        return tagsDto;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public TagDto findTagById(@PathVariable long id) {
        TagDto tagDto = tagService.findById(id);
        addDependenciesLinks(tagDto);
        return tagDto;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/popular")
    public TagDto findMostPopularTag() {
        TagDto tagDto = tagService.findMostPopular();
        addDependenciesLinks(tagDto);
        return tagDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto addTag(@RequestBody TagDto tagDto) {
        TagDto tagDtoResult = tagService.add(tagDto);
        addDependenciesLinks(tagDtoResult);
        return tagDtoResult;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTagById(@PathVariable long id) {
        tagService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addDependenciesLinks(TagDto tagDto) {
        tagDto.add(linkTo(methodOn(TagController.class).findTagById(tagDto.getId())).withSelfRel());
        tagDto.add(linkTo(methodOn(TagController.class).deleteTagById(tagDto.getId())).withRel("delete"));
    }
}
