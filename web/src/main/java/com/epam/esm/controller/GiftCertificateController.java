package com.epam.esm.controller;

import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.dto.GiftCertificateQueryParametersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/gift-certificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificateDto> findAllGiftCertificates(@RequestParam(required = false) String tagName,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String description,
                                                            @RequestParam(required = false)
                                                                    GiftCertificateQueryParametersDto.TypeSort typeSort,
                                                            @RequestParam(required = false)
                                                                    GiftCertificateQueryParametersDto.OrderSort orderSort) {
        GiftCertificateQueryParametersDto giftCertificateQueryParametersDto = new GiftCertificateQueryParametersDto();
        giftCertificateQueryParametersDto.setTagName(tagName);
        giftCertificateQueryParametersDto.setName(name);
        giftCertificateQueryParametersDto.setDescription(description);
        giftCertificateQueryParametersDto.setTypeSort(typeSort);
        giftCertificateQueryParametersDto.setOrderSort(orderSort);
        return giftCertificateService.findAll(giftCertificateQueryParametersDto);
    }

    @GetMapping("/{id}")
    public GiftCertificateDto findGiftCertificateById(@PathVariable long id) {
        return giftCertificateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto addGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        return giftCertificateService.add(giftCertificateDto);
    }

    @PutMapping(value = "/{id}")
    public GiftCertificateDto updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setId(id);
        return giftCertificateService.update(giftCertificateDto);
    }

    @PatchMapping(value = "/{id}")
    public GiftCertificateDto updatePartOfGiftCertificate(@PathVariable long id, @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setId(id);
        return giftCertificateService.updatePart(giftCertificateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificate(@PathVariable long id) {
        giftCertificateService.remove(id);
    }

}
