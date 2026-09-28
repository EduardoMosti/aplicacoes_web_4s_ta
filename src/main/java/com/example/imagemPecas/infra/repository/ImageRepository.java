package com.example.imagemPecas.infra.repository;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>,
        JpaSpecificationExecutor<Image> {

    @Query("""
        SELECT i FROM Image i 
        WHERE (i.extension = :extension OR :extension IS NULL)
          AND (UPPER(i.name) LIKE UPPER(CONCAT('%', :query, '%')) 
               OR UPPER(i.tags) LIKE UPPER(CONCAT('%', :query, '%'))
               OR :query IS NULL)
    """)
    List<Image> findByExtensionAndNameOrTagsLike(
            @Param("extension") ImageExtension extension,
            @Param("query") String query
    );
}