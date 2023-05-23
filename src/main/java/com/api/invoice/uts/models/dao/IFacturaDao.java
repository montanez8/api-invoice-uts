package com.api.invoice.uts.models.dao;

import com.api.invoice.uts.models.entities.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}