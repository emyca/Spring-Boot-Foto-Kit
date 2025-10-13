package com.example.Spring_Boot_Foto_Kit.service.admin;

import com.example.Spring_Boot_Foto_Kit.data.FotoData;
import com.example.Spring_Boot_Foto_Kit.data.FotoMessage;
import com.example.Spring_Boot_Foto_Kit.exceptions.FotoException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FotoValidator {

    /**
     * Validates image file.
     *
     * @param data incoming Foto data
     * */
    public void validateImage(FotoData data) {
        MultipartFile file = data.getFile();
        if (file == null || file.isEmpty())
            throw new FotoException(
                    FotoMessage.ADD_FILE_MSG.getMessage());
    }

    /**
     * Validates image name.
     *
     * @param data incoming Foto data
     * */
    public void validateName(FotoData data) {
        String name = data.getName();
        if (name == null || name.isBlank())
            throw new FotoException(
                    FotoMessage.ADD_NAME_MSG.getMessage());
    }

    /**
     * Validates image description.
     *
     * @param data incoming Foto data
     * */
    public void validateDescription(FotoData data) {
        String description = data.getDescription();
        if (description == null || description.isBlank())
            throw new FotoException(
                    FotoMessage.ADD_DESCRIPTION_MSG.getMessage());
    }
}
