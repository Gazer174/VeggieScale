import java.util.Arrays;

public class Product {
    public boolean equalsIgnoreCase;
    private String name;
    private double price;
    private String[] category;
   

    public Product(String[] category){
        this.category = category;
    }
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public Product (String name, double price, String[] category){
        this.name = name;
        this.price = price;
        this.category = category;
    }



    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCategory() {
        return Arrays.toString(this.category);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    @Override
    public String toString(){

        if (category != null && price !=0){
            return this.name + " " + this.price + "kr/kg i kategorin "+ Arrays.toString(this.category);
        }
        if (category != null){
            return this.name + "i kategorin " + Arrays.toString(this.category) + " *inget pris inlagt*";
        }
        if (price != 0 ){
            return this.name + " " + this.price + "kr/kg";
        } else {
            return this.name;
        }

    }

}
