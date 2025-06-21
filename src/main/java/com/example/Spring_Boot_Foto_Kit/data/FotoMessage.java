package com.example.Spring_Boot_Foto_Kit.data;

import lombok.Getter;

@Getter
public enum FotoMessage {

    SUCCESS_CREATE_MSG("Foto has been created."),
    FAILURE_CREATE_MSG("Foto has not been created."),
    FAILURE_GET_ITEM_MSG("Foto has not been found!"),
    FAILURE_GET_LIST_MSG("Fotos have not been found!"),
    SUCCESS_UPDATE_MSG("Foto has been updated."),
    SUCCESS_DELETE_MSG("Foto has been deleted.");

    private final String message;

    FotoMessage(String message) {
        this.message = message;
    }
}
