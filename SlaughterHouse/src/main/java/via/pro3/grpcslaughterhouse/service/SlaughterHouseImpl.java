package via.pro3.grpcslaughterhouse.service;

import io.grpc.stub.StreamObserver;
import via.pro3.grpcslaughterhouse.shared.Animal;
import via.pro3.grpcslaughterhouse.shared.Product;
import via.pro3.slaughterhouse.grpc.AnimalIdRequest;
import via.pro3.slaughterhouse.grpc.AnimalIdRequest;
import via.pro3.slaughterhouse.grpc.ProductIdRequest;

import via.pro3.slaughterhouse.grpc.SlaughterHouseResponse;
import via.pro3.slaughterhouse.grpc.SlaughterHouseServiceGrpc;

import java.sql.SQLException;
import java.util.ArrayList;

public class SlaughterHouseImpl extends SlaughterHouseServiceGrpc.SlaughterHouseServiceImplBase {

    private final DatabaseConnector databaseConnector;

    public SlaughterHouseImpl() throws SQLException {
        this.databaseConnector = new DatabaseConnector();
    }

    @Override
    public void getAnimalsByProductId(ProductIdRequest request, StreamObserver<SlaughterHouseResponse> responseObserver) {
        int productId = request.getProductId();
        System.out.println("Product ID: " + productId);

        ArrayList<Animal> animals = databaseConnector.getAnimalsByProductId(productId);
        animals.forEach(animal -> System.out.println("Animal ID: " + animal.getAnimalId() + ", Weight: " + animal.getWeight() + ", Arrival Date: " + animal.getArrivalDate() + ", Status: " + animal.getStatus()));

        StringBuilder messageBuilder = new StringBuilder("Animals retrieved:\n");
        for (Animal animal : animals) {
            messageBuilder.append("Animal ID: ").append(animal.getAnimalId())
                    .append(", Weight: ").append(animal.getWeight())
                    .append(", Arrival Date: ").append(animal.getArrivalDate())
                    .append(", Status: ").append(animal.getStatus())
                    .append("\n");
        }

        System.out.println("Response: " + messageBuilder.toString());
        SlaughterHouseResponse response = SlaughterHouseResponse.newBuilder()
                .setMessage(messageBuilder.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductsByAnimalId(AnimalIdRequest request, StreamObserver<SlaughterHouseResponse> responseObserver){
        int animalId = request.getAnimalId();
        System.out.println("Animal ID: " + animalId);

        ArrayList<Product> products = databaseConnector.getProductsByAnimalId(animalId);
        products.forEach(product -> System.out.println("Product ID: " + product.getProductId() + ", Type: " + product.getType()));

        StringBuilder messageBuilder = new StringBuilder("Products retrieved:\n");
        for (Product product : products) {
            messageBuilder.append("Product ID: ").append(product.getProductId())
                    .append(", Type: ").append(product.getType())
                    .append("\n");
        }

        System.out.println("Response: " + messageBuilder.toString());
        SlaughterHouseResponse response = SlaughterHouseResponse.newBuilder()
                .setMessage(messageBuilder.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}