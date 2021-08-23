package com.api.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AuthorRequestDTO implements Serializable {

    private String id;

    @Length(min =3 , max = 50, message = "Max 50 caracteres")
    @NotEmpty(message = "Campo nome é obrigatório")
    private String name;

    @CPF(message = "Invalid CPF")
    @Column(unique = true, nullable = true)
    private String cpf;

    @Length(min =3 ,max = 50, message = "Max 50 caracteres")
    @NotEmpty(message = "field nationality can not be empty")
    private String nationality;

    @Length(min =3 ,max = 250, message = "Max 250 caracteres")
    @NotEmpty(message = "field biography can not be empty")
    private String biography;

    @Column
    @Email(message = "invalid e-mail", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @Column @NotNull
    @Past(message = "The date of bith must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy")
    private LocalDate birthdate;


    public AuthorRequestDTO() {
    }

    public AuthorRequestDTO(String id, String name, String cpf, String nationality, String biography, String email, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.nationality = nationality;
        this.biography = biography;
        this.email = email;
        this.birthdate = birthdate;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getNationality() {
//        return nationality;
//    }
//
//    public void setNationality(String nationality) {
//        this.nationality = nationality;
//    }
//
//    public String getBiography() {
//        return biography;
//    }
//
//    public void setBiography(String biography) {
//        this.biography = biography;
//    }
//
//    public LocalDate getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(LocalDate birthdate) {
//        this.birthdate = birthdate;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
