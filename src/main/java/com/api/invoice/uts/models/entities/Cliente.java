package com.api.invoice.uts.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "clientes")
public class Cliente implements java.io.Serializable {// Serializable:
    // Convertir un objeto en una secuencia de bytes para poder guardarlo en un archivo, enviarlo a través de una red.
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Long id;
    @Column(nullable = false)// Not null
    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 3, max = 30, message = "el tamaño tiene que estar entre 3 y 30")
    private String nombre;
    @Column(nullable = false) // Not null
    private String apellido;
    @Column(nullable = false, unique = true) // Not null and unique
    @Email(message = "no es una dirección de correo bien formada")
    private String email;
    @Column(name = "create_at") // Column name
    private Date createAt;

    @NotNull(message = "la región no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID = 1L;
}
