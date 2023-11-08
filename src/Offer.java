import java.io.*;
import java.util.Scanner;

public class Offer {

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
        createNewOffer();

    }



    public String getMaxOrMini() {
        return maxOrMini;
    }

    public void setMaxOrMini(String maxOrMini) {
        this.maxOrMini = maxOrMini;
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
            //System.out.println("Kampanj skapad och sparad i 'OfferDir'");

            //skriver i kvitto-fil
            FileWriter fileWriter = new FileWriter(offerFile);
            fileWriter.write(toString());

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("kunde inte skapa kampanj");
        }

    }

    public void getOfferFromFile() throws FileNotFoundException {
        File file = new File("OfferDir\\" + name + ".txt");
        Scanner sc = new Scanner(file);
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            boolean offerProduct = false;
            while ((line = bufferedReader.readLine()) != null){
                if (line.contains("Kampanj!!! apelsin 1.0 kr kg i kategorin FRUKT max 3 per kund Ordinarie Pris 20.0 ")) {
                    System.out.println("detta är en kampanj vara");
                    offerProduct = true;
                }
            }
            Main.tmplist.add(line);
        } catch (IOException e) {
            System.out.println("Hittade inget erbjudande!!!" + e);
            throw new RuntimeException(e);
        }

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
        return "Kampanj!!! " + name + " " + text + " Ordinarie Pris " + regularPrice + "kr/" + name.getUnit();

    }
}
