package org.example.restapi.service;

import org.example.restapi.Repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.restapi.shared.Animal;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal saveAnimal(Animal animal){
        return animalRepository.save(animal);
    }

    public Animal getAnimalById(int animalId){
        return animalRepository.findById(animalId).orElse(null);
    }

    public List<Animal> getAnimalsByArrivalDate(String arrivalDate){
         return animalRepository.findAnimalsByArrivalDate(arrivalDate);
    };

    public List<Animal> getAnimalsByWeight(float weight){
        return animalRepository.findAnimalsByWeight(weight);
    }

    public List<Animal> getAnimalsByStatus(String status){
        return animalRepository.findAnimalsByStatus(status);
    }

    public List<Animal> getAnimalsByOrigin(String origin){
        return animalRepository.findAnimalsByOrigin(origin);
    }
}
