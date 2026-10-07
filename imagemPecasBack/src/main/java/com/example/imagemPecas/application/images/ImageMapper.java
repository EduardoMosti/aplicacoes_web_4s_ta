package com.example.imagemPecas.application.images;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {

    // Converte MultipartFile + metadados → Image (entidade)
    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException {
        return Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.fromMediaType(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
    }

    // Converte Image → ImageDTO (resposta API)
    public ImageDTO imageToDTO(Image image, String url) {
        String extension = image.getExtension() != null
                ? image.getExtension().name()
                : "UNKNOWN";

        return ImageDTO.builder()
                .url(url)
                .extension(extension)
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }
}