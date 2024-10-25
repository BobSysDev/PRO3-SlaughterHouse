import org.junit.Before;
import org.junit.Test;
import via.pro3.grpcslaughterhouse.service.DatabaseConnector;
import via.pro3.grpcslaughterhouse.shared.Animal;
import via.pro3.grpcslaughterhouse.shared.Product;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DataBaseConnectorClass {

    private DatabaseConnector databaseConnector;

    @Before
    public void setUp() throws SQLException {
        databaseConnector = new DatabaseConnector();
    }

    @Test
    public void testGetAnimalsByProductId() {
        int productID = 2;
        ArrayList<Animal> animals = databaseConnector.getAnimalsByProductId(productID);

        assertNotNull(animals);
        System.out.println("Number of animals: " + animals.size());
        assertNotEquals(0, animals.size());

        for(Animal animal : animals){
            assertNotNull(animal);
            assertNotNull(animal.getArrivalDate());
            assertNotNull(animal.getStatus());
            assertNotEquals(animal.getAnimalId(), animal.getWeight());
        }

    }

    @Test
    public void testGetProductsByAnimalId() {
        int animalID = 1;
        ArrayList<Product> products = databaseConnector.getProductsByAnimalId(animalID);

        assertNotNull(products);
        assertNotEquals(0, products.size());

        for (Product product : products){
            assertNotNull(product);
            assertNotNull(product.getType());
            assertNotEquals(product.getProductId(), product.getType());
        }
    }
}
