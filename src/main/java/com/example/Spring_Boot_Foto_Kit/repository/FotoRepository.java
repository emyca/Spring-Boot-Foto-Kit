package com.example.Spring_Boot_Foto_Kit.repository;

import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends CrudRepository<Foto,Long> {
}
