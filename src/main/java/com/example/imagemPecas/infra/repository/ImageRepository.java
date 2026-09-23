package com.example.imagemPecas.infra.repository;

import com.example.imagemPecas.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>,
        JpaSpecificationExecutor<Image> {
}