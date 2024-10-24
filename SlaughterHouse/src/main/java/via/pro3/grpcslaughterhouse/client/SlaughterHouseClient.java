package via.pro3.grpcslaughterhouse.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import via.pro3.grpcslaughterhouse.shared.Animal;
import via.pro3.slaughterhouse.grpc.ProductIdRequest;
import via.pro3.slaughterhouse.grpc.AnimalIdRequest;
import via.pro3.slaughterhouse.grpc.SlaughterHouseResponse;
import via.pro3.slaughterhouse.grpc.SlaughterHouseServiceGrpc;

import java.util.ArrayList;
import java.util.Scanner;

public class SlaughterHouseClient {

    private final SlaughterHouseServiceGrpc.SlaughterHouseServiceBlockingStub blockingStub;

    public SlaughterHouseClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = SlaughterHouseServiceGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SlaughterHouseClient client = new SlaughterHouseClient("localhost", 50051);

        while (true) {

            int choice = -1;
            while (true) {
                System.out.println("=======[ MENU ]=======");
                System.out.println("1: retrieve animals involved in a product");
                System.out.println("2: retrieve products an animal was involved in");
                System.out.println("3: exit");
                System.out.print("Your choice -> ");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 3) {
                        break;
                    }
                    System.out.println("Input out of range. Please, try again.");
                } catch (Exception e) {
                    System.out.println("Invalid input. Please, try again.");
                }
            }

            switch (choice) {
                case 1 -> {
                    int id = -1;
                    while (true) {
                        System.out.print("Input the ID of the product: ");
                        try {
                            id = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please, try again.");
                        }
                    }

                    client.requestAnimalsByProductId(id);
                }
                case 2 -> {
                    int id = -1;
                    while (true) {
                        System.out.print("Input the ID of the animal: ");
                        try {
                            id = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please, try again.");
                        }
                    }
                    client.requestProductsByAnimalId(id);
                }
                case 3 -> {
                    return;
                }
            }
        }
    }

    private void requestAnimalsByProductId(int productId) {
        ProductIdRequest request = ProductIdRequest.newBuilder()
                .setProductId(productId)
                .build();

        SlaughterHouseResponse response;
        try {
            response = blockingStub.getAnimalsByProductId(request);
            System.out.println("Response: " + response.getMessage());
        } catch (Exception e) {
            System.err.println("RPC failed: " + e.getMessage());
        }
    }

    private void requestProductsByAnimalId(int animalId) {
        AnimalIdRequest request = AnimalIdRequest.newBuilder()
                .setAnimalId(animalId)
                .build();

        SlaughterHouseResponse response;
        try {
            response = blockingStub.getProductsByAnimalId(request);
            System.out.println("Response: " + response.getMessage());
        } catch (Exception e) {
            System.err.println("RPC failed: " + e.getMessage());
        }
    }
}