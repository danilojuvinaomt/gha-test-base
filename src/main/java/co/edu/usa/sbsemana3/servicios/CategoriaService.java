/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.servicios;

import co.edu.usa.sbsemana3.modelo.Categoria;
import co.edu.usa.sbsemana3.modelo.Producto;
import co.edu.usa.sbsemana3.repositories.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juvinao
 */
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    
    public List<Categoria> getAll(){
        return categoriaRepository.getAll();
    }
    
    public Categoria getById(int id){
        Optional<Categoria> categoria = categoriaRepository.getById(id);
        return categoria.orElse(null);
//        if (producto.isPresent()){
//            return producto.get();
//        }else{
//            return null;
//        }
    }
    
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    
    public boolean delete(int id){
        
        Boolean d= categoriaRepository.getById(id).map(category -> {
            categoriaRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    
    }
    
    public Categoria update(Categoria categoria){
        if(categoria.getCodigo()!=null){
            Optional<Categoria> cat= categoriaRepository.getById(categoria.getCodigo());
            if(cat.isPresent()){
                if(categoria.getNombre()!=null){
                    cat.get().setNombre(categoria.getNombre());
                }
                return categoriaRepository.save(cat.get());
            }
        }
        return categoria;
    }
    
}
