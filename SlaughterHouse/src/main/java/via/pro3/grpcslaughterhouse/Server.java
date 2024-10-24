package via.pro3.grpcslaughterhouse;

import io.grpc.ServerBuilder;
import via.pro3.grpcslaughterhouse.service.SlaughterHouseImpl;

public class Server {

    public static void main(String[] args) throws Exception {
        SlaughterHouseImpl converter = new SlaughterHouseImpl();
        io.grpc.Server server = ServerBuilder
                .forPort(50051)
                .addService(converter)
                .build();
        server.start();
        System.out.println("Server running");

        server.awaitTermination();
    }
}
