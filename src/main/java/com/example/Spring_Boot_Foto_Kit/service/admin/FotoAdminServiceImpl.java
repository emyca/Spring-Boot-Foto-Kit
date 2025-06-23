package com.example.Spring_Boot_Foto_Kit.service.admin;

import com.example.Spring_Boot_Foto_Kit.data.FotoRequest;
import com.example.Spring_Boot_Foto_Kit.data.FotoResponse;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoAdminServiceImpl implements FotoAdminService {

    @Override
    public FotoResponse add(FotoRequest request) {
        return null;
    }

    @Override
    public List<Foto> getAll() {
        return List.of();
    }

    @Override
    public FotoResponse update(Long id, FotoRequest request) {
        return null;
    }

    @Override
    public FotoResponse delete(Long id) {
        return null;
    }
}
