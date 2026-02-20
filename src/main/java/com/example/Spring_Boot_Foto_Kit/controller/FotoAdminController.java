package com.example.Spring_Boot_Foto_Kit.controller;

import com.example.Spring_Boot_Foto_Kit.data.FotoMessage;
import com.example.Spring_Boot_Foto_Kit.data.FotoRequest;
import com.example.Spring_Boot_Foto_Kit.data.FotoResponse;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.service.admin.FotoAdminService;
import com.example.Spring_Boot_Foto_Kit.utils.URLS;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class FotoAdminController {

    private  final FotoAdminService service;

    public FotoAdminController(FotoAdminService service) {
        this.service = service;
    }

    /**
     * Returns view fragment with data handled
     *
     * @param model supply attributes used for rendering views
     * @return      view fragment
     * */
    @GetMapping("/fotos")
    public String getAll(Model model) {
        List<Foto> list = service.getAll();
        model.addAttribute("title", "Admin | Fotos");
        if (list.isEmpty()) {
            model.addAttribute("fotoInfo",
                    FotoMessage.FAILURE_GET_LIST_MSG.getMessage());
        } else {
            model.addAttribute("uploads", "../" +
                    URLS.UPLOADS_URL.getUrl());
            model.addAttribute("fotos", list);
        }
        model.addAttribute("fragmentName", "content");
        return "admin/@layout";
    }

    /**
     * Uploads an image and it's data to server
     *
     * @param request image data
     * @param file    image file
     * @return        spring response entity of Foto response type
     * */
    @PostMapping(path = "/fotos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoResponse> add(
            @ModelAttribute FotoRequest request,
            @RequestParam("file") MultipartFile file) {
        request.setFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.add(request));
    }

    /**
     * Updates image data by id
     *
     * @param id      image id
     * @param request image data
     * @return        spring response entity of Foto response type
     * */
    @PutMapping(path = "/fotos/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoResponse> update(
            @PathVariable("id") Long id,
            @RequestBody FotoRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, request));
    }

    /**
     * Deletes image and it's data by id
     *
     * @param id image id
     * @return   spring response entity of Foto response type
     * */
    @DeleteMapping(path = "/fotos/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoResponse> delete(
            @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }

}
