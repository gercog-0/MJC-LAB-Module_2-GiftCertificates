package com.epam.esm.controller;

import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDto> findAllTags(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return tagService.findAll(paginationDto);
    }

    @GetMapping("/{id}")
    public TagDto findTagById(@PathVariable long id) {
        return tagService.findById(id);
    }

    @GetMapping("/popular")
    public TagDto findMostPopularTag() {
        return tagService.findMostPopular();
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
}
