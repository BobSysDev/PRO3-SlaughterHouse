package org.example.restapi.shared;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Animal {

    @Id
    private int animalId;
    private float weight;
    private String arrivalDate;
    private String status;
    private String origin;

    public Animal(int animalId, float weight, String arrivalDate , String status, String origin){
        this.animalId = animalId;
        this.weight = weight;
        this.arrivalDate = arrivalDate;
        this.status = status;
        this.origin= origin;
    }

}
