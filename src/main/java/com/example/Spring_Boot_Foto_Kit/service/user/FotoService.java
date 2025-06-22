package com.example.Spring_Boot_Foto_Kit.service.user;

import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FotoService {
    List<Foto> getAll();
}
