package com.api.web.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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


}
