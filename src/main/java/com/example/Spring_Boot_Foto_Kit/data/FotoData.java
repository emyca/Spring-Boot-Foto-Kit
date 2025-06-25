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
        this.id = (request.getId() != null) ? request.getId() : null;
        this.file = (request.getFile() != null) ? request.getFile() : null;
        this.name = (request.getName() != null) ? request.getName() : null;
        this.description = (request.getDescription() != null) ? request.getDescription() : null;
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
