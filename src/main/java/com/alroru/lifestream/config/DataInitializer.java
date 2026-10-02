package com.alroru.lifestream.config;

import com.alroru.lifestream.model.Animal;
import com.alroru.lifestream.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    final AnimalRepository repository;

    public DataInitializer(AnimalRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        Animal zorro=new Animal("Zorro rojo", "Vulpes vulpes", "Bosques y praderas", "Omnívoro", "Preocupación menor", "Cánido pequeño y adaptable de pelaje rojizo y cola frondosa");
        Animal lobo =new Animal("Lobo ibérico", "Canis lupus signatus", "Bosques y montañas", "Carnívoro", "Casi amenazado", "Subespecie de lobo de la península ibérica que vive en manadas");
        Animal lince =new Animal("Lince ibérico", "Lynx pardinus", "Matorral mediterráneo", "Carnívoro", "En peligro", "Felino moteado de orejas con pinceles, especialista en conejo");
        repository.save(zorro);
        repository.save(lobo);
        repository.save(lince);

    }
}
