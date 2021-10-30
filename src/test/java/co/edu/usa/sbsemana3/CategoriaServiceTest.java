/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3;

import co.edu.usa.sbsemana3.modelo.Categoria;
import co.edu.usa.sbsemana3.servicios.CategoriaService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author juvinao
 */
@SpringBootTest
@ActiveProfiles("devh2")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaServiceTest {

    @Autowired
    CategoriaService service;

    @BeforeEach
    void initUseCase() {
//        service = new CategoriaService();
    }

    @Test
    @Order(1)
    public void getAllEmpty() {
        System.out.println("getAllTestEmpty");
        List<Categoria> cats = service.getAll();
        Assertions.assertEquals(0, cats.size());
    }

    @Test
    @Order(2)
    public void saveThreeAndValidate() {
        System.out.println("getSaveTestThreeAndValidate");
        Categoria cat1 = new Categoria();
        cat1.setNombre("Alimentos");
        Categoria cat2 = new Categoria();
        cat2.setNombre("Alimentos");
        Categoria cat3 = new Categoria();
        cat3.setNombre("Alimentos");

        service.save(cat1);
        service.save(cat2);
        service.save(cat3);

        List<Categoria> cats = service.getAll();
        Assertions.assertEquals(3, cats.size());
    }

    @Test
    @Order(3)
    public void updateAndValidate() {
        System.out.println("getUpdateAndValidate");
        Categoria cat = service.getById(1);
        cat.setNombre("Vehiculos");
        service.save(cat);

        List<Categoria> cats = service.getAll();
        Assertions.assertEquals(3, cats.size());
        Categoria catReconsultado = service.getById(1);
        Assertions.assertEquals("Vehiculos", catReconsultado.getNombre());
    }

    @Test
    @Order(4)
    public void deleteAndValidate() {
        boolean res = service.delete(1);
        Assertions.assertEquals(true, res);
        List<Categoria> cats = service.getAll();
        Assertions.assertEquals(2, cats.size());
    }

    @Test
    @Order(5)
    public void tryDeleteNoValid() {
        boolean res = service.delete(100);
        Assertions.assertEquals(false, res);
        List<Categoria> cats = service.getAll();
        Assertions.assertEquals(2, cats.size());
    } 

    @Test
    @Order(6)
    public void tryGetByIdValid() {
        Categoria categoria = service.getById(2);
        Assertions.assertNotNull(categoria);
    }

    @Test
    @Order(7)
    public void tryGetByIdNoValid() {
        Categoria categoria = service.getById(85);
        Assertions.assertNull(categoria);
    }

    @Test
    @Order(8)
    public void tryUpdateWithNoValidId() {
        Categoria categoria = new Categoria();
        categoria.setCodigo(60);
        categoria.setNombre("test category");
        service.update(categoria);
        Categoria cat = service.getById(60);
        Assertions.assertNull(cat);

    }

}
