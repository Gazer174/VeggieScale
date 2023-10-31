import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Offer {
    private String name;
    private double offerPrice;
    private String text;
    private double regularPrice;

    public Offer(String name, double offerPrice, double regularPrice, String text) {
        this.name = name;
        this.offerPrice = offerPrice;
        this.text = text;
        this.regularPrice = regularPrice;
        createNewOffer();
    }
    public void createNewOffer() {
        File offerFile;
        File offerDir = new File("OfferDir");

        if(!offerDir.exists()){
            boolean created = offerDir.mkdirs();
            if(created){
                //System.out.println("Kvitto mapp skapad framgångsrikt");
            } else {
                //System.out.println("Mappen skapades inte");
            }
        } else {
            // System.out.println("Mappen finns redan");
        }
        try {
            //skapar kvitto-fil

            offerFile = new File(offerDir, this.name + ".txt");
            System.out.println("Kampanj skapad");

            //skriver i kvitto-fil
            FileWriter fileWriter = new FileWriter(offerFile);
            fileWriter.write(toString());

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("kunde inte skapa kampanj");
        }

    }

    public double getRegularPrice() {
        return regularPrice;
    }



    public String getName() {
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
        return "Kampanj!!! " + name + " Kampanjpris " + offerPrice + " " + text + " Ordinarie Pris " + regularPrice + " ";

    }
}
