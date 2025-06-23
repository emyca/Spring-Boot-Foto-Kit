package com.example.Spring_Boot_Foto_Kit.service.admin;

import com.example.Spring_Boot_Foto_Kit.data.FotoRequest;
import com.example.Spring_Boot_Foto_Kit.data.FotoResponse;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FotoAdminService {
    List<Foto> getAll();
    FotoResponse add(FotoRequest request);
    FotoResponse update(Long id, FotoRequest request);
    FotoResponse delete(Long id);
}
