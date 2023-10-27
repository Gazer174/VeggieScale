import java.util.ArrayList;
import java.util.Date;

public class Receipt {
    private int number;
    private int Date;
    private ArrayList<Product> shoppingCart;
    private int nOfReceipts;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getnOfReceipts() {
        return nOfReceipts;
    }

    public void setnOfReceipts(int nOfReceipts) {
        this.nOfReceipts = nOfReceipts;
    }
}
