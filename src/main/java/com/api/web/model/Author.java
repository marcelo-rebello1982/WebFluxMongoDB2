package com.api.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Document
public class Author extends BaseEntity implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    @CPF(message = "Invalid CPF")
    @Column(unique = true, nullable = true)
    private String cpf;

    @Column
    private String nationality;

    @Column
    private String biography;

    @Column
    private String email;

    @Column @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

    @ManyToOne
    private Category category;

    public Author() {
    }

    public Author(String id, String name, String nationality, String biography, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
        this.birthdate = birthdate;
    }


    public Author(String id, String name, String cpf, String nationality, String biography, String email, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.nationality = nationality;
        this.biography = biography;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Author(String id, String name, String nationality, String biography) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
    }

    public Author(String name, String nationality, String biography, LocalDate birthdate) {
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
        this.birthdate = birthdate;
    }

    public Author(String name, String nationality, String biography) {
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
    }

    public Author(String id) {
        this.id = id;
    }



    public Author(String id, String name, String nationality, String biography, String email, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
        this.email = email;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public Author mappingAuthorToEntity(Author newAuthor) {
        newAuthor.setName(getName());
        newAuthor.setBirthdate(getBirthdate());
        newAuthor.setBiography(getBiography());
        newAuthor.setNationality(getNationality());
        newAuthor.setEmail(getEmail());
        newAuthor.setCpf(getCpf());
        return newAuthor;
    }
//
//    public Author mappingAuthorToEntity(Author a) {
//        Author author = new Author();
//        author.setId(a.getId());
//        author.setName(a.getName());
//        author.setBiography(a.getBiography());
//        author.setNationality(a.getNationality());
//        author.setBirthdate(a.getBirthdate());
//        return author;
//    }


    static Author mappingEntityToAuthor(Author author) {
        return new Author(
                author.getId(),
                author.getName(),
                author.getBiography(),
                author.getNationality(),
                author.getEmail(),
                author.getBirthdate());
    }


}
