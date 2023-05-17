package com.api.invoice.uts.controllers;

import com.api.invoice.uts.models.entities.Cliente;
import com.api.invoice.uts.models.entities.Region;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.api.invoice.uts.services.IclienteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class clienteRestController {
    @Autowired
    private IclienteService clienteService;
    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Cliente show(@PathVariable Long id) {
        return clienteService.findById(id);
    }
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){

        Cliente clienteNew=null;

        Map<String, Object> response=new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " +err.getField() +" "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            clienteNew= this.clienteService.save(cliente);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido creado con éxito!");
        response.put("cliente", clienteNew);

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }
    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,BindingResult result,@PathVariable  Long id){

        Cliente currentCliente=this.clienteService.findById(id);

        Cliente updateCliente=null;

        Map<String, Object> response=new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " +err.getField() +" "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }

        if(currentCliente==null){
            response.put("mensaje", "Error: no se puede editar, el cliente ID: ".concat(id.toString())
                    .concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }

        try{
            currentCliente.setNombre(cliente.getNombre());
            currentCliente.setApellido(cliente.getApellido());
            currentCliente.setEmail(cliente.getEmail());
            updateCliente=this.clienteService.save(currentCliente);

        }catch(DataAccessException e){
            response.put("mensaje", "Error al actulizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje","El cliente ha sido actulizado con éxito!");
        response.put("cliente", updateCliente);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response=new HashMap<>();
        try {

            Cliente cliente=this.clienteService.findById(id);
           this.clienteService.delete(cliente.getId());

        }catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente eliminado con éxito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    @GetMapping("clientes/regiones")
    public List<Region> ListarRegiones() {
        return clienteService.findAllRegiones();
    }
}
