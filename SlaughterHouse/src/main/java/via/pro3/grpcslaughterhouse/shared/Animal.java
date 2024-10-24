package via.pro3.grpcslaughterhouse.shared;
import java.io.Serializable;

public class Animal implements Serializable {
    private int animalId;
    private float weight;
    private String arrivalDate;
    private String status;

    public Animal(int animalId, float weight, String arrivalDate , String status){
        this.animalId = animalId;
        this.weight = weight;
        this.arrivalDate = arrivalDate;
        this.status = status;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
