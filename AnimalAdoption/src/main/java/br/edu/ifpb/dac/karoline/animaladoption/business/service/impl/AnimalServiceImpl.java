package br.edu.ifpb.dac.karoline.animaladoption.business.service.impl;

import br.edu.ifpb.dac.karoline.animaladoption.business.service.AnimalService;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        Animal existingAnimal = animalRepository.findById(id).orElse(null);
        if (existingAnimal != null) {
            existingAnimal.setName(updatedAnimal.getName());
            existingAnimal.setSpecies(updatedAnimal.getSpecies());
            existingAnimal.setDescription(updatedAnimal.getDescription());
            existingAnimal.setOwner(updatedAnimal.getOwner());

            return animalRepository.save(existingAnimal);
        } else {
            return null;
        }
    }
    public void deleteAnimal(Long animalId) {
        animalRepository.deleteById(animalId);
    }
}