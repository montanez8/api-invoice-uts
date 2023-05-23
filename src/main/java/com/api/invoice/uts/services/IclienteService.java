package com.api.invoice.uts.services;

import com.api.invoice.uts.models.entities.Cliente;
import com.api.invoice.uts.models.entities.Factura;
import com.api.invoice.uts.models.entities.Producto;
import com.api.invoice.uts.models.entities.Region;

import java.util.List;

public interface IclienteService {
    public List<Cliente> findAll();

    public Cliente findById(Long id);

    public Cliente save(Cliente cliente);

    public void delete(Long id);

    public List<Region> findAllRegiones();

    public Factura findFacturaById(Long id);

    public List<Factura> findFacturaAll();

    public Factura saveFactura(Factura factura);

    public void deleteFacturaById(Long id);

    public List<Producto> findProductoByNombre(String term);

}
