package com.example.imagemPecas.application.images;

import com.example.imagemPecas.domain.enums.ImageExtension;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ImageFilterRequest {
    private ImageExtension extension;
    private String name;
    private LocalDateTime uploadDateAfter;
}