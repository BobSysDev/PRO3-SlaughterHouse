package via.pro3.grpcslaughterhouse.shared;

public class Product {
    private int productId;
    private String type;

    public Product(int productId, String type){
        this.productId = productId;
        this.type = type;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
