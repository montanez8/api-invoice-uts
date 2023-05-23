package com.api.invoice.uts.models.dao;

import com.api.invoice.uts.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
}
