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

    /**
     * Transforms request data to entity .
     *
     * @return New item object to save into DB
     */
    public Foto requestToEntity() {
        Foto foto = new Foto();
        foto.setImg(this.getFile().getOriginalFilename());
        foto.setName(this.getName());
        foto.setDescription(this.getDescription());
        return foto;
    }

    /**
     * Updates item data got from DB.
     *
     * @param id Item primary key in DB
     * @param request Request body
     * @param foto Item object got from DB
     *
     * @return Updated item object to save into DB
     */
    public Foto updateRequestToEntity(
            Long id,
            FotoRequest request,
            Foto foto
    ) {
        foto.setId(id);
        foto.setImg(foto.getImg());
        foto.setName(request.getName());
        foto.setDescription(request.getDescription());
        return foto;
    }

    /**
     * Transforms Iterable item collection
     * to List item collection.
     *
     * @return List item collection
     */
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
