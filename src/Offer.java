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
    /*
    public Offer() {
        try {
            getOfferFromFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

     */



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

            offerFile = new File(offerDir, this.name.getName() + ".txt");
            //System.out.println("Kampanj skapad och sparad i 'OfferDir'");

            //skriver i kvitto-fil
            FileWriter fileWriter = new FileWriter(offerFile);
            fileWriter.write(toString());

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("kunde inte skapa kampanj");
        }

    }
/*
    public void getOfferFromFile() throws FileNotFoundException {

        File folder = new File("OfferDir");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }


            File fileToRead = new File("OfferDir\\"+ file.getName());
            Scanner sc = new Scanner(fileToRead);
            BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader(fileToRead));
            String line;

            boolean offerProduct = false;
            while ((line = bufferedReader.readLine()) != null){
                if (line.contains(toString())) {
                    System.out.println("detta är en kampanj vara");
                    offerProduct = true;
                }
            }
            Main.tmpList.add(line);
        } catch (IOException e) {
            System.out.println("Hittade inget erbjudande!!!" + e);
            throw new RuntimeException(e);
        }

    }

    }

 */







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
