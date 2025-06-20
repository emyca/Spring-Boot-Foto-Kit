package com.example.Spring_Boot_Foto_Kit.data;

import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

// DTO that contains values of a specific item
@Getter
public class FotoData {

    private Long id;
    private MultipartFile image;
    private String name;
    private String description;
    private int status;
    private String reasonPhrase;
    private boolean success;
    private String message;

    public FotoData(FotoRequest request) {
        this.id = (request.id() != null) ? request.id() : null;
        this.image = (request.image() != null) ? request.image() : null;
        this.name = (request.name() != null) ? request.name() : null;
        this.description = (request.description() != null) ? request.description() : null;
    }

    public Foto requestToEntity() {
        Foto foto = new Foto();
        foto.setImg(this.getImage().getOriginalFilename());
        foto.setName(this.getName());
        foto.setDescription(this.getDescription());
        return foto;
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Foto has been created."),
        FAILURE_CREATE_MSG("Foto has not been created."),
        FAILURE_GET_ITEM_MSG("Foto has not been found!"),
        SUCCESS_UPDATE_MSG("Foto has been updated."),
        SUCCESS_DELETE_MSG("Foto has been deleted.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

}
