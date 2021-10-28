/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.servicios;

import co.edu.usa.sbsemana3.modelo.Categoria;
import co.edu.usa.sbsemana3.modelo.Producto;
import co.edu.usa.sbsemana3.repositories.CategoriaRepository;
import co.edu.usa.sbsemana3.repositories.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juvinao
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> getAll() {
        return productoRepository.getAll();
    }

    public Producto getById(int id) {
        Optional<Producto> producto = productoRepository.getById(id);
        return producto.orElse(new Producto());
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);

    }

    public boolean delete(int id) {
        Boolean d = productoRepository.getById(id).map(producto -> {
            productoRepository.delete(producto);
            return true;
        }).orElse(false);
        return d;
    }

    public Producto update(Producto producto) {
        if (producto.getCodigo() != null) {
            Optional<Producto> prod = productoRepository.getById(producto.getCodigo());
            if (prod.isPresent()) {
                System.out.println("si encontro el producto con el codigo " + producto.getCodigo());
                if (producto.getNombre() != null) {
                    prod.get().setNombre(producto.getNombre());
                }
                if (producto.getPrecio() != null) {
                    prod.get().setPrecio(producto.getPrecio());
                }
                if (producto.getInventario() != null) {
                    prod.get().setInventario(producto.getInventario());
                }

                if (producto.getCategoria().getCodigo() != null) {
                    Optional<Categoria> cat = categoriaRepository.getById(producto.getCategoria().getCodigo());
                    if (cat.isPresent()) {
                        prod.get().setCategoria(cat.get());
                    }
                }

                return productoRepository.save(prod.get());
            }
        }
        return producto;
    }

}
