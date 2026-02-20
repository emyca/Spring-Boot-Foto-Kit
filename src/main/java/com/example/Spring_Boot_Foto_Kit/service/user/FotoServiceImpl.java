package com.example.Spring_Boot_Foto_Kit.service.user;

import com.example.Spring_Boot_Foto_Kit.data.FotoData;
import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import com.example.Spring_Boot_Foto_Kit.repository.FotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FotoServiceImpl implements FotoService {

    private final FotoRepository repository;

    public FotoServiceImpl(FotoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Foto> getAll() {
        return new FotoData()
                .toList(repository.findAll());
    }
}
