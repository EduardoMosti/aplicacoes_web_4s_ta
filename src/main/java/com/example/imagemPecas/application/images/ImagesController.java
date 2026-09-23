package com.example.imagemPecas.application.images;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.service.ImageService;
import com.example.imagemPecas.domain.service.ImageSpecifications;
import com.example.imagemPecas.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;
    private final ImageMapper mapper;
    private final ImageRepository repository;

    // POST: salvar imagem
    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException {
        log.info("Imagem recebida: name: {}, size: {}, tags: {}",
                file.getOriginalFilename(), file.getSize(), tags);

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);

        URI imageUri = buildImageURL(savedImage);
        return ResponseEntity.created(imageUri).build();
    }

    // GET: recuperar imagem por ID
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        var possibleImage = service.getById(id);

        if (possibleImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    // GET SEARCH: buscar com filtros dinâmicos
    @GetMapping("/search")
    public ResponseEntity<List<ImageDTO>> search(@ModelAttribute ImageFilterRequest filter) {
        Specification<Image> spec =(root, query, cb) -> cb.conjunction();

        if (filter.getExtension() != null) {
            spec = spec.and(ImageSpecifications.hasExtension(filter.getExtension()));
        }

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            spec = spec.and(ImageSpecifications.nameContains(filter.getName()));
        }

        if (filter.getUploadDateAfter() != null) {
            spec = spec.and(ImageSpecifications.uploadedAfter(filter.getUploadDateAfter()));
        }

        List<Image> images = repository.findAll(spec);

        List<ImageDTO> dtos = images.stream()
                .map(image -> mapper.imageToDTO(image, buildImageURL(image).toString()))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Helper: construir URL da imagem
    private URI buildImageURL(Image image) {
                return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/images/" + image.getId())
                .build()
                .toUri();
    }
}