/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.web;

import co.edu.usa.sbsemana3.modelo.Categoria;
import co.edu.usa.sbsemana3.modelo.Compra;
import co.edu.usa.sbsemana3.modelo.custom.CountCompraByTipoCliente;
import co.edu.usa.sbsemana3.servicios.CompraService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juvinao
 */
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService service;

    @GetMapping("/all")
    private List<Compra> getAll() {
        return service.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Compra save(@RequestBody Compra compra) {
        return service.save(compra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Compra update(@RequestBody Compra compra) {
        return service.update(compra);
    }

    @GetMapping("/report/{dateOne}/{dateTwo}")
    public List<Compra> getComprasReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return service.getComprasByPeriod(dateOne, dateTwo);
    }

    @GetMapping("/report/compras-by-tipo")
    public List<CountCompraByTipoCliente> getComprasByTipo() {
        return service.getComprasByTipoCliente();
    }
}
