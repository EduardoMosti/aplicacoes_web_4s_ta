package com.example.imagemPecas.infra.repository.specs;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecs {
    private ImageSpecs(){

    }
    public static Specification<Image> extensionEqual(ImageExtension extension) {
        return (root, query, cb) -> cb.equal(root.get("extension"), extension);
    }
    public static Specification<Image> nameLike(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
    public static Specification<Image> tagsLike(String tags) {
        return ((root, query, cb) ->
                cb.like(cb.lower(root.get("tags")), "%" + tags.toLowerCase() + "%"));
    }
}
