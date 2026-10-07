package com.example.imagemPecas.domain.service;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ImageSpecifications {

    // Filtro 1: extensão
    public static Specification<Image> hasExtension(ImageExtension extension) {
        return (root, query, cb) -> cb.equal(root.get("extension"), extension);
    }

    // Filtro 2: nome contém
    public static Specification<Image> nameContains(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    // Filtro 3: data posterior
    public static Specification<Image> uploadedAfter(LocalDateTime date) {
        return (root, query, cb) -> cb.greaterThan(root.get("uploadDate"), date);
    }
}