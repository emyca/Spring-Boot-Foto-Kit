package com.example.Spring_Boot_Foto_Kit.service.admin;

import com.example.Spring_Boot_Foto_Kit.data.FotoData;
import com.example.Spring_Boot_Foto_Kit.data.FotoRequest;
import com.example.Spring_Boot_Foto_Kit.data.FotoResponse;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FotoAdminServiceImpl implements FotoAdminService {

    @Autowired
    FotoRepository repository;

    @Override
    @Transactional
    public List<Foto> getAll() {
        return new FotoData()
                .toList(repository.findAll());
    }

    @Override
    public FotoResponse add(FotoRequest request) {
        return null;
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
