/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.repositories;

import co.edu.usa.sbsemana3.modelo.Categoria;
import co.edu.usa.sbsemana3.repositories.crud.CategoriaCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juvinao
 */
@Repository
public class CategoriaRepository {

    @Autowired
    private CategoriaCrudRepository repo;

    public List<Categoria> getAll() {
        return (List<Categoria>) repo.findAll();
    }
    
    public Optional<Categoria> getById(int id) {
        return repo.findById(id);
    }
    
    public Categoria save(Categoria producto){
        return repo.save(producto);
    }
    
    public void delete(Categoria categoria){
        repo.delete(categoria);
    }
}
