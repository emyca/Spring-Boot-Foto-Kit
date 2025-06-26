package com.example.Spring_Boot_Foto_Kit.controller;

import com.example.Spring_Boot_Foto_Kit.data.FotoMessage;
import com.example.Spring_Boot_Foto_Kit.data.FotoRequest;
import com.example.Spring_Boot_Foto_Kit.data.FotoResponse;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.service.admin.FotoAdminService;
import com.example.Spring_Boot_Foto_Kit.utils.URLS;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    FotoAdminService service;

    @GetMapping("/fotos")
    public String getAll(Model model) {
        List<Foto> list = service.getAll();
        model.addAttribute("title", "Admin | Fotos");
        if (list.isEmpty()) {
            model.addAttribute("fotoInfo",
                    FotoMessage.FAILURE_GET_LIST_MSG.getMessage());
        } else {
            model.addAttribute("uploads",
                    URLS.GET_UPLOADS_URL.getUrl());
            model.addAttribute("fotos", list);
        }
        model.addAttribute("fragmentName", "content");
        return "admin/@layout";
    }

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

    @PutMapping(path = "/fotos/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoResponse> update(
            @PathVariable("id") Long id,
            @RequestBody FotoRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, request));
    }

    @DeleteMapping(path = "/fotos/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoResponse> delete(
            @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }

}
