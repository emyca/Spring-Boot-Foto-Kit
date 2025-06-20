package com.example.Spring_Boot_Foto_Kit.data;

public record FotoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message
) {
}
