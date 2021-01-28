package com.epam.esm.controller;

import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/tags")
public class TagController implements LinkRelationship<TagDto> {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDto> findAllTags(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        List<TagDto> tagsDto = tagService.findAll(paginationDto);
        tagsDto.forEach(this::addDependenciesLinks);
        return tagsDto;
    }

    @GetMapping("/{id}")
    public TagDto findTagById(@PathVariable long id) {
        TagDto tagDto = tagService.findById(id);
        addDependenciesLinks(tagDto);
        return tagService.findById(id);
    }

    @GetMapping("/popular")
    public TagDto findMostPopularTag() {
        TagDto tagDto = tagService.findMostPopular();
        addDependenciesLinks(tagDto);
        return tagDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto addTag(@RequestBody TagDto tagDto) {
        return tagService.add(tagDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable long id) {
        tagService.remove(id);
    }

    @Override
    public void addDependenciesLinks(TagDto tagDto) {
        tagDto.add(linkTo(methodOn(TagController.class).findTagById(tagDto.getId())).withSelfRel());
    }
}
