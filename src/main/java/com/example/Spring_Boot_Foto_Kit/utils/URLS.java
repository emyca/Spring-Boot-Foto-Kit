package com.example.Spring_Boot_Foto_Kit.utils;

import lombok.Getter;

@Getter
public enum URLS {

    GET_UPLOADS_URL("../uploads/"),
    TO_UPLOADS_URL("uploads/");

    private final String url;

    URLS(String url) {
        this.url = url;
    }
}
