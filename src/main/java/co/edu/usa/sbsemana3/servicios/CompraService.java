/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.servicios;

import co.edu.usa.sbsemana3.modelo.Compra;
import co.edu.usa.sbsemana3.modelo.custom.CountCompraByTipoCliente;
import co.edu.usa.sbsemana3.repositories.CompraRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juvinao
 */
@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> getAll() {
        return compraRepository.getAll();
    }

    public Compra getById(int id) {
        Optional<Compra> compra = compraRepository.getById(id);
        return compra.orElse(null);
    }

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public boolean delete(int id) {

        Boolean d = compraRepository.getById(id).map(compra -> {
            compraRepository.delete(compra);
            return true;
        }).orElse(false);
        return d;

    }

    public Compra update(Compra compra) {
        if (compra.getCodigo() != null) {
            Optional<Compra> cat = compraRepository.getById(compra.getCodigo());
            if (cat.isPresent()) {
                if (compra.getFechaCompra() != null) {
                    cat.get().setFechaCompra(compra.getFechaCompra());
                }
                if (compra.getValorCompra() != null) {
                    cat.get().setValorCompra(compra.getValorCompra());
                }
                return compraRepository.save(cat.get());
            }
        }
        return compra;
    }

    public List<Compra> getComprasByPeriod(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();
        try {
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (a.before(b)) {
            return compraRepository.getCompraByPeriod(a, b);
        } else {
            return new ArrayList<>();
        }
    }

    public List<CountCompraByTipoCliente> getComprasByTipoCliente() {
        return compraRepository.getComprasByTipoCliente();
    }

}
