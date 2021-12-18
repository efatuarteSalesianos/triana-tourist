package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.modelos.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryDTOConverter {

    private final POIDTOConverter poidtoConverter;

    public GetCategoryDTO categoryToGetCategoryDTO(Category category) {
        return GetCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .sitios(category.getSitios().stream().map(poidtoConverter::poiToGetPOIListDTO).collect(Collectors.toList()))
                .build();
    }

    public GetCategoryListDTO categoryToGetCategoryListDTO(Category category) {
        return GetCategoryListDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .pois(category.getSitios().stream().map(poidtoConverter::poiToGetPOIListDTO).collect(Collectors.toList()))
                .build();
    }

    public Category saveCategoryDtoToCategory(SaveCategoryDTO category) {
        return Category.builder()
                .name(category.getName())
                .build();
    }
}
