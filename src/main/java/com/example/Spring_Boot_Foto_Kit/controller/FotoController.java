package com.example.Spring_Boot_Foto_Kit.controller;

import com.example.Spring_Boot_Foto_Kit.data.FotoMessage;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.service.user.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FotoController {

    @Autowired
    FotoService service;

    @GetMapping("/fotos")
    public String getAll(Model model) {
        List<Foto> list = service.getAll();
        model.addAttribute("title", "Fotos");
        if (list.isEmpty()) {
            model.addAttribute("fotoInfo",
                    FotoMessage.FAILURE_GET_LIST_MSG.getMessage());
        } else {
            model.addAttribute("fotos", list);
        }
        model.addAttribute("fragmentName", "foto-content");
        return "user/@layout";
    }
}
