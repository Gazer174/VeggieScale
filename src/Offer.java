import java.io.*;
import java.util.Scanner;

public class Offer implements  Serializable{

    private Product name;
    private double offerPrice;
    private String text;
    private double regularPrice;
    private double offerLimit;
    private String maxOrMini;
    public Offer(Product name, double offerPrice, double regularPrice, String text, double offerLimit, String maxOrMini) {
        this.name = name;
        this.offerPrice = offerPrice;
        this.text = text;
        this.regularPrice = regularPrice;
        this.offerLimit = offerLimit;
        this.maxOrMini = maxOrMini;


    }

    public String getMaxOrMini() {
        return maxOrMini;
    }

    public void setMaxOrMini(String maxOrMini) {
        this.maxOrMini = maxOrMini;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public double getOfferLimit() {
        return offerLimit;
    }

    public void setOfferLimit(double offerLimit) {
        this.offerLimit = offerLimit;
    }

    public Product getName(){
        return name;
    }


    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {

        return "Kampanj!!! " + name.getName() + " " + getOfferPrice() + " kr/"+ name.getUnit() + " " + text + " Ordinarie Pris " + regularPrice + "kr/" + name.getUnit();

    }
}
