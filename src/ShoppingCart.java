import java.util.ArrayList;

public class ShoppingCart {
    private double totalCost;
    private int totalItems;
    private ArrayList<Product> listOfItems;

    public ShoppingCart(double totalCost, int totalItems, ArrayList<Product> listOfItems) {
        this.totalCost = totalCost;
        this.totalItems = totalItems;
        this.listOfItems = listOfItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<Product> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(ArrayList<Product> listOfItems) {
        this.listOfItems = listOfItems;
    }

    @Override
    public String toString() {
        return  listOfItems +
                "\nAntal varor: " + totalItems +
                "\nTotala kostnad: " + totalCost + "kr";

    }
}
