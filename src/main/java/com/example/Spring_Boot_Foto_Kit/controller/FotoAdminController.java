package com.example.Spring_Boot_Foto_Kit.controller;

import com.example.Spring_Boot_Foto_Kit.data.FotoMessage;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.service.admin.FotoAdminService;
import com.example.Spring_Boot_Foto_Kit.utils.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

}
