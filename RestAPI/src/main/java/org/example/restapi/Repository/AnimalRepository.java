package org.example.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.restapi.shared.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {

    List<Animal> findAnimalsByWeight(float weight);
    List<Animal> findAnimalsByArrivalDate(String arrivalDate);
    List<Animal> findAnimalsByStatus(String status);
    List<Animal> findAnimalsByOrigin(String origin);
}
