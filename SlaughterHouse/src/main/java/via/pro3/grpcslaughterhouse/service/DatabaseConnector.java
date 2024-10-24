package via.pro3.grpcslaughterhouse.service;

import via.pro3.grpcslaughterhouse.shared.Animal;
import via.pro3.grpcslaughterhouse.shared.Product;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnector {

    public static final String POSTGRES_PASSWORD = "zxcasdqwe123";

    public DatabaseConnector() throws SQLException {
        try (Connection connection = getConnection()) {
            System.out.println(connection.isValid(5) ? "Database connection established successfully." : "Database connection could not be established. Check the PostgreSQL password and try again.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(2137);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=MeatHouse", "postgres", POSTGRES_PASSWORD);
    }

//    public String retrieveAnimals() {
//        StringBuilder result = new StringBuilder();
//        try (Connection connection = getConnection()){
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "SELECT * FROM animals;");
//             ResultSet keys = preparedStatement.executeQuery();
//
//            while (keys.next()) {
//                result.append(keys.getInt("animal_id")).append(" ");
//                result.append(keys.getFloat("weight")).append(" ");
//                result.append(keys.getString("status")).append(" ");
//                result.append(keys.getDate("arrival_date"));
//                result.append("\n");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query: " + e.getMessage());
//        }
//        return result.toString();
//    }

//    public String retrieveProducts() {
//        StringBuilder result = new StringBuilder();
//        try (Connection connection = getConnection()){
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "SELECT * FROM products;");
//             ResultSet keys = preparedStatement.executeQuery();
//
//            while (keys.next()) {
//                result.append(keys.getInt("product_id")).append(" ");
//                result.append(keys.getString("type")).append(" ");
//                result.append("\n");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query: " + e.getMessage());
//        }
//        return result.toString();
//    }

//    public String retrieveTrays() {
//        StringBuilder result = new StringBuilder();
//        try (Connection connection = getConnection()){
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM trays;");
//            ResultSet keys = preparedStatement.executeQuery();
//
//            while (keys.next()) {
//                result.append(keys.getInt("tray_id")).append(" ");
//                result.append(keys.getInt("max_capacity")).append(" ");
//                result.append(keys.getInt("current_capacity")).append(" ");
//                result.append(keys.getString("part_type")).append(" ");
//                result.append("\n");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query: " + e.getMessage());
//        }
//        return result.toString();
//    }
//}

    public ArrayList<Animal> getAnimalsByProductId(int productId) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                
                    SELECT animals.animal_id, animals.weight, animals.arrival_date, animals.status FROM animals
                        INNER JOIN animal_parts ON animals.animal_id = animal_parts.animal_id
                        INNER JOIN partstrays ON animal_parts.part_id = partstrays.part_id
                        INNER JOIN traysproducts ON partstrays.tray_id = traysproducts.tray_id
                        INNER JOIN products ON traysproducts.product_id = products.product_id
                WHERE products.product_id = ?"""
            );
            preparedStatement.setInt(1, productId);
            ResultSet keys = preparedStatement.executeQuery();

            ArrayList<Animal> animalsToReturn = new ArrayList<>();


            while(keys.next()){
                Animal animal = new Animal(
                        keys.getInt("animal_id"),
                        keys.getFloat("weight"),
                        keys.getDate("arrival_date").toString(),
                        keys.getString("status")
                );
                animalsToReturn.add(animal);
            }

            return animalsToReturn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Product> getProductsByAnimalId(int animalId){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                
                    SELECT products.product_id, products.type FROM products
                        INNER JOIN traysproducts ON traysproducts.product_id = products.product_id
                        INNER JOIN partstrays ON partstrays.tray_id = traysproducts.tray_id
                        INNER JOIN animal_parts ON animal_parts.part_id = partstrays.part_id
                        INNER JOIN animals ON animals.animal_id = animal_parts.animal_id
                WHERE animals.animal_id = ?"""
            );
            preparedStatement.setInt(1, animalId);
            ResultSet keys = preparedStatement.executeQuery();

            ArrayList<Product> productsToReturn = new ArrayList<>();


            while(keys.next()){
                Product product = new Product(
                        keys.getInt("product_id"),
                        keys.getString("type")
                );
                productsToReturn.add(product);
            }

            return productsToReturn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

