package co.edu.usa.sbsemana3;

import co.edu.usa.sbsemana3.modelo.Categoria;
import co.edu.usa.sbsemana3.modelo.Producto;
import co.edu.usa.sbsemana3.repositories.CategoriaRepository;
import co.edu.usa.sbsemana3.repositories.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.usa.sbsemana3"})
public class Sbsemana3Application {

    @Autowired
    private ProductoRepository repo;

    @Autowired
    private CategoriaRepository repoCategoria;
    
    public static void main(String[] args) {
        SpringApplication.run(Sbsemana3Application.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            List<Producto> ps = repo.getAll();
            System.out.println("Productos: "+ps.size());
            
            List<Categoria> cs = repoCategoria.getAll();
            System.out.println("Categorias: "+cs.size());
        }; 
    }
    
}
