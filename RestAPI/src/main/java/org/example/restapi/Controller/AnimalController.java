package org.example.restapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.restapi.service.AnimalService;
import org.example.restapi.shared.Animal;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public Animal registerAnimal(@RequestBody Animal animal){
        return animalService.saveAnimal(animal);
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping("/weight/{weight}")
    public List<Animal> getAnimalByWeight(@PathVariable float weight){
        return animalService.getAnimalsByWeight(weight);
    }

    @GetMapping("/arrivalDate/{arrivalDate}")
    public List<Animal> getAnimalByArrivalDate(@PathVariable String arrivalDate){
        return animalService.getAnimalsByArrivalDate(arrivalDate);
    }

    @GetMapping("/status/{status}")
    public List<Animal> getAnimalsByStatus(@PathVariable String status){
        return animalService.getAnimalsByStatus(status);
    }



}
