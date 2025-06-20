package com.example.Spring_Boot_Foto_Kit.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FotoRequest(
        Long id,
        MultipartFile image,
        String name,
        String description
) {
}
