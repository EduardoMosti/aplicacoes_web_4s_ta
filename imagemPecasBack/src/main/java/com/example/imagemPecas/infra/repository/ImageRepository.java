package com.example.imagemPecas.infra.repository;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import com.example.imagemPecas.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query) {
        Specification<Image> spec = null;

        if (extension != null) {
            spec = ImageSpecs.extensionEqual(extension);
        }

        if (query != null && !query.trim().isEmpty()) {
            Specification<Image> querySpec = Specification
                    .where(ImageSpecs.nameLike(query))
                    .or(ImageSpecs.tagsLike(query));

            if (spec != null) {
                spec = Specification.where(spec).and(querySpec);
            } else {
                spec = querySpec;
            }
        }

        if (spec == null) {
            return findAll();
        }

        return findAll(spec);
    }
}