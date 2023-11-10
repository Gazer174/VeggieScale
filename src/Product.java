import java.io.Serializable;
import java.util.Arrays;

public class  Product  implements Serializable {
    private String name;
    private double price;
    private String category;
    private String unit;
    private double totalOfProducts;
   

    public Product  (String name, double price, String unit)  {
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

    public Product(Product product, double price, double totalOfProducts, String unit) {
        this.name = product.getName();
        this.price = price;
        this.totalOfProducts = totalOfProducts;
        this.unit = unit;
    }

    public double getTotalOfProducts() {
        return totalOfProducts;
    }

    public void setTotalOfProducts(double totalOfProducts) {
        this.totalOfProducts = totalOfProducts;
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



   /*
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", unit='" + unit + '\'' +
                ", totalOfProducts=" + totalOfProducts +
                '}';
    }

    */
    @Override
    public String toString(){

        if (category != null && price !=0){
            return this.name + " " + this.price + "kr per " + this.unit;// + " i kategorin " + this.category;
        }
        if (category != null){
            return this.name + " i kategorin " + this.category + " *inget pris inlagt*";
        }
        if (price != 0 ){
            return this.name + " " + this.price + " kr " + this.totalOfProducts + this.unit;
        }
        else {
            return this.name;
        }

    }



}
