import java.util.Arrays;

public class Product {
    private String name;
    private double price;
    private String kampanj;
    private String category;
    private String unit;
   

    public Product(String name, double price, String unit){
        this.name = name;
        this.price = price;
        this.unit = unit;
    }
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public Product (String name, double price, String unit, String category){
        this.name = name;
        this.price = price;
        this.category = category;
        this.unit = unit;
    }

    public Product(Product product, double price) {
        this.name = product.getName();
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString(){

        if (category != null && price !=0){
            return this.name + " " + this.price + " kr " + this.unit + " i kategorin " + this.category;
        }
        if (category != null){
            return this.name + " i kategorin " + this.category + " *inget pris inlagt*";
        }
        if (price != 0 ){
            return this.name + " " + this.price + " kr ";
        }
        else {
            return this.name;
        }

    }

}
