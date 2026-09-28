package com.example.imagemPecas.domain.enums;

import lombok.Getter;
import org.springframework.http.MediaType;
import java.util.Arrays;

public enum ImageExtension {
    PNG(MediaType.IMAGE_PNG),
    JPG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF);

    @Getter
    private final MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension fromMediaType(MediaType mediaType) {
        return Arrays.stream(values())
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

    public static ImageExtension ofName(String name) {
        if (name == null) {
            return null;
        }
        try {
            return ImageExtension.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}