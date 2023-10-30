import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Receipt {

    private ShoppingCart boughtItems;

    public Receipt(ShoppingCart boughtItems) {
        this.boughtItems = boughtItems;
        createNewReceipt();
    }


    public String getDate() {
        Date a = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat();
        String str = formatter.format(a);
        return str;
    }

    public void createNewReceipt() {
        File receiptFile;
        File receiptDir = new File("receiptdir");

        if(!receiptDir.exists()){
            boolean created = receiptDir.mkdirs();
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

            receiptFile = new File(receiptDir, getFormattedTime() + ".txt");
            System.out.println("Kvitto är utskrivet");

            //skriver i kvitto-fil
            FileWriter fileWriter = new FileWriter(receiptFile);
            fileWriter.write(getDate() + "\n" + boughtItems);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("kunde inte skriva ut kvitto");
        }

    }
    public long getFormattedTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = dateTime.format(formatter);
        long valueOfFormatter = Long.parseLong(formattedDateTime);

        return valueOfFormatter;
    }
}
