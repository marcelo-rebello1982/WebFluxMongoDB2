package com.api.web.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class MapperUtility {

    private MapperUtility() {}

    static Author toAuthor(Author author) {
        return new Author(

                author.getId(),
                author.getName(),
                author.getCpf(),
                author.getNationality(),
                author.getBiography(),
                author.getEmail(),
                author.getBirthdate());
    }

    public Author mappingAuthorToEntity(Author old, Author updated) {
        updated.setName(old.getName());
        updated.setBirthdate(old.getBirthdate());
        updated.setBiography(old.getBiography());
        updated.setNationality(old.getNationality());
        updated.setEmail(old.getEmail());
        return updated;
    }

    public static Author mappingAuthorToEntity(@NotNull Author o) {
        Objects.requireNonNull(o);
        Author newAuthor = new Author();
        newAuthor.setId(o.getId());
        newAuthor.setName(o.getName());
        newAuthor.setNationality(o.getNationality());
        newAuthor.setBiography(o.getBiography());
        newAuthor.setEmail(o.getEmail());
        return newAuthor;
    }
}
