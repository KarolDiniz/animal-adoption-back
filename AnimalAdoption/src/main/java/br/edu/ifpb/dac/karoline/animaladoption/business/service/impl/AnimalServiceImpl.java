package br.edu.ifpb.dac.karoline.animaladoption.business.service.impl;

import br.edu.ifpb.dac.karoline.animaladoption.business.service.AnimalService;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.DTOConverterService;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.repository.AnimalRepository;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private DTOConverterService dtoConverter;

    public AnimalDTO create(AnimalDTO animalDTO) {
        Animal animal = dtoConverter.convertToAnimal(animalDTO);
        animal = animalRepository.save(animal);
        AnimalDTO createdAnimalDTO = dtoConverter.convertToAnimalDTO(animal);
        return createdAnimalDTO;
    }

    public List<AnimalDTO> getAll() {
        List<Animal> animals = animalRepository.findAll();

        return animals.stream()
                .map(dtoConverter::convertToAnimalDTO)
                .collect(Collectors.toList());
    }

    public AnimalDTO getById(Long id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal != null) {
            return dtoConverter.convertToAnimalDTO(animal);
        } else {
            return null;
        }
    }

    @Override
    public AnimalDTO update(Long id, AnimalDTO updatedAnimal) {
        Animal existingAnimal = animalRepository.findById(id).orElse(null);

        if (existingAnimal != null) {
            existingAnimal.setName(updatedAnimal.getName());
            existingAnimal.setSpecies(updatedAnimal.getSpecies());
            existingAnimal.setDescription(updatedAnimal.getDescription());

            Animal updatedAnimalEntity = animalRepository.save(existingAnimal);

            return dtoConverter.convertToAnimalDTO(updatedAnimalEntity);
        } else {
            return null;
        }
    }
    public boolean delete(Long animalId) {
        try {
            animalRepository.deleteById(animalId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete animal with ID " + animalId, e);
        }
    }

}