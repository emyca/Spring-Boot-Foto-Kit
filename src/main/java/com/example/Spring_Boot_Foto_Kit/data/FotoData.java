package com.example.Spring_Boot_Foto_Kit.data;

import com.example.Spring_Boot_Foto_Kit.entity.Foto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

// DTO that contains values of a specific item
@Getter
@NoArgsConstructor
public class FotoData {

    private Long id;
    private MultipartFile file;
    private String name;
    private String description;

    public FotoData(FotoRequest request) {
        this.id = (request.id() != null) ? request.id() : null;
        this.file = (request.file() != null) ? request.file() : null;
        this.name = (request.name() != null) ? request.name() : null;
        this.description = (request.description() != null) ? request.description() : null;
    }

    public Foto requestToEntity() {
        Foto foto = new Foto();
        foto.setImg(this.getFile().getOriginalFilename());
        foto.setName(this.getName());
        foto.setDescription(this.getDescription());
        return foto;
    }

    public List<Foto> toList(Iterable<Foto> iterable) {
        List<Foto> list =
                StreamSupport
                        .stream(iterable.spliterator(), false)
                        .map(foto ->
                                new Foto(
                                    foto.getId(),
                                    foto.getImg(),
                                    foto.getName(),
                                    foto.getDescription()))
                        .toList();
        return new ArrayList<>(list);
    }
}
