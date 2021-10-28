/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.repositories;

import co.edu.usa.sbsemana3.modelo.Compra;
import co.edu.usa.sbsemana3.modelo.custom.CountCompraByTipoCliente;
import co.edu.usa.sbsemana3.repositories.crud.CompraCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juvinao
 */
@Repository
public class CompraRepository {

    @Autowired
    private CompraCrudRepository repo;

    public List<Compra> getAll() {
        return (List<Compra>) repo.findAll();
    }

    public Optional<Compra> getById(int id) {
        return repo.findById(id);
    }

    public Compra save(Compra compra) {
        return repo.save(compra);
    }

    public void delete(Compra compra) {
        repo.delete(compra);
    }

    public List<Compra> getCompraByPeriod(Date a, Date b) {
        return repo.findAllByFechaCompraAfterAndFechaCompraBefore(a, b);
    }

    public List<CountCompraByTipoCliente> getComprasByTipoCliente() {
        List<CountCompraByTipoCliente> res = new ArrayList<>();
        List<Object[]> report = repo.countComprasTotalesByTipoCliente();
        for (int i = 0; i < report.size(); i++) {
            Object[] linea = report.get(i);
            res.add(new CountCompraByTipoCliente(linea[0].toString(), (Long) linea[1]));
        }
        return res;
    }
}
