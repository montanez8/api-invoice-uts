package com.api.invoice.uts.models.dao;

import com.api.invoice.uts.models.entities.Cliente;
import com.api.invoice.uts.models.entities.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IclienteDao extends CrudRepository<Cliente, Long> {
    // CrudRepository: Interfaz que nos permite realizar operaciones CRUD (Create, Read, Update, Delete) sobre la entidad Cliente.
    // Cliente: Clase que representa la entidad Cliente.
    // Long: Tipo de dato de la clave primaria de la entidad Cliente.
    @Query("from Region")
    public List<Region> findAllRegiones();


}
