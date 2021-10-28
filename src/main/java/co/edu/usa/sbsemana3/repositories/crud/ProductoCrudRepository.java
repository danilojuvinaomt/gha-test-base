/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.repositories.crud;

import co.edu.usa.sbsemana3.modelo.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author juvinao
 */
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    
    @Query(value="select * from productos where categoria_id = ? order by nombre ASC", nativeQuery = true)
    public List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
}
