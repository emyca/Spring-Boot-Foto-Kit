package com.example.Spring_Boot_Foto_Kit.service.admin;

import com.example.Spring_Boot_Foto_Kit.data.FotoData;
import com.example.Spring_Boot_Foto_Kit.data.FotoMessage;
import com.example.Spring_Boot_Foto_Kit.data.FotoRequest;
import com.example.Spring_Boot_Foto_Kit.data.FotoResponse;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.exceptions.FotoException;
import com.example.Spring_Boot_Foto_Kit.repository.FotoRepository;
import com.example.Spring_Boot_Foto_Kit.utils.StringGenerator;
import com.example.Spring_Boot_Foto_Kit.utils.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class FotoAdminServiceImpl implements FotoAdminService {

    @Autowired
    FotoValidator validator;
    @Autowired
    FotoRepository repository;

    @Override
    @Transactional
    public List<Foto> getAll() {
        return new FotoData()
                .toList(repository.findAll());
    }

    @Override
    @Transactional
    public FotoResponse add(FotoRequest request) {
        FotoData data = new FotoData(request);

        try {
            this.validator.validateImage(data);
        } catch (FotoException e) {
            return new FotoResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false, e.getMessage());
        }

        try {
            this.validator.validateName(data);
        } catch (FotoException e) {
            return new FotoResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false, e.getMessage());
        }

        try {
            this.validator.validateDescription(data);
        } catch (FotoException e) {
            return new FotoResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false, e.getMessage());
        }

        try {
            // Gets item entity object
            Foto foto = data.requestToEntity();
            // Sets random string prefix to image file
            // to make it unique
            String uploadedImgWithPrefix =
                    StringGenerator.genStr() + "-" + foto.getImg();
            // Gets image file bytes
            byte[] bytes = data.getFile().getBytes();
            // Gets uploads path
            Path relativePath = Paths.get(
                    URLS.UPLOADS_URL.getUrl()
                            + uploadedImgWithPrefix);
            Path absolutePath = relativePath.toAbsolutePath();
            // Writes image file to uploads folder
            Files.write(absolutePath, bytes);
            // Sets image file name with random prefix
            // to item entity object
            foto.setImg(uploadedImgWithPrefix);
            // Calls repository method to save item entity object
            // into DB
            repository.save(foto);
            return new FotoResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true,
                    FotoMessage.SUCCESS_CREATE_MSG.getMessage());
        } catch (FotoException | IOException e) {
            return new FotoResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false,
//                    FotoMessage.FAILURE_CREATE_MSG.getMessage()
                    e.getMessage()
            );
        }
    }

    @Override
    @Transactional
    public FotoResponse update(Long id, FotoRequest request) {
        FotoData data = new FotoData(request);

        try {
            this.validator.validateName(data);
        } catch (FotoException e) {
            return new FotoResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false, e.getMessage());
        }

        try {
            this.validator.validateDescription(data);
        } catch (FotoException e) {
            return new FotoResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false, e.getMessage());
        }

        // Gets wrapped by Optional
        // item entity object to update
        // from DB by id
        Optional<Foto> optional = repository.findById(id);

        if (optional.isPresent()) {
            // Gets updated specific item object
            Foto foto =
                    data.updateRequestToEntity(
                            id, request, optional.get());
            // Saves item entity object into DB
            repository.save(foto);
            return new FotoResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true,
                    FotoMessage.SUCCESS_UPDATE_MSG.getMessage());
        } else
            return new FotoResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false,
                    FotoMessage.FAILURE_GET_ITEM_MSG.getMessage());
    }

    @Override
    @Transactional
    public FotoResponse delete(Long id) {
        Optional<Foto> optional = repository.findById(id);
        if (optional.isPresent()) {
            // Gets item file name
            String fileName = optional.get().getImg();
            // Gets file path
            Path path = Paths.get(
                    URLS.UPLOADS_URL.getUrl()
                            + fileName);
            try {
                // Deletes the file
                Files.delete(path);
            } catch (IOException e) {
                return new FotoResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false,
                        "No file found!");
            }

            // Deletes item from DB
            repository.deleteById(id);
            return new FotoResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true,
                    FotoMessage.SUCCESS_DELETE_MSG.getMessage());
        } else
            return new FotoResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false,
                    FotoMessage.FAILURE_GET_ITEM_MSG.getMessage());
    }
}
