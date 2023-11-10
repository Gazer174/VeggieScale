import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;

public class Main {
    public static ArrayList<Product> listOfProducts = new ArrayList<Product>();
    public static ArrayList<Product> shoppingItems = new ArrayList<Product>();
    public static ArrayList<Offer> offerList = new ArrayList<Offer>();
    public static ArrayList<String> tmpList = new ArrayList<>();


    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        //addProductsForTesting();
        readProductListToFile();
        readOfferListToFile();

        //offerFromFile();
        boolean exitMenu = false;
        do {
            System.out.println("Välkommen till Matbutiken");
            System.out.println("0. Avsluta Program");
            System.out.println("1. Gå in i Affären");
            System.out.println("2. Logga in som Administratör");
            System.out.println("Välj en siffra: ");
            boolean choice = false;
            while (!choice) {
                try {
                    int menuChoice = input.nextInt();
                    input.nextLine();
                    choice = true;
                    switch (menuChoice) {
                        case 0 -> exitMenu = true;
                        case 1 -> costumerSite();
                        case 2 -> loginChoice();

                    }
                } catch (Exception e) {
                    System.out.print("felaktig inmatning, försök igen: \nSkriv menysiffra!: ");
                    input.next();
                }
            }
        } while (!exitMenu);
        System.out.println("Avslutar program...\nHA EN FORTSATT TREVLIG DAG!");
        System.out.println("Program avslutat");

    }


    public static void loginChoice() throws IOException {
        System.out.println("välj ett alternativ nedan, ditt lösenord kommer att synas om du använder IntelliJ Konsolen");
        System.out.println("1. Jag använder IntelliJ Konsolen");
        System.out.println("2. Jag använder kommandotolken");
        System.out.println("Ditt val: ");
        int userChoice = input.nextInt();
        input.nextLine();
        switch (userChoice) {
            case 1 -> intelliJLogin();
            case 2 -> cmdLogin();
        }

    }

    public static boolean intelliJLogin() throws IOException {
        int maxTry = 3;

        boolean found = false;

            do {
                System.out.println("Du har " + maxTry + " försök!");
                System.out.println("Skriv användarnamn: ");
                String username = checkIfBlankOrExit();
                System.out.println("Skriv lösenord: ");
                String password = checkIfBlankOrExit();
                maxTry--;

                found = Admin.getUserInfo(username, password);
                if (found) {
                    adminSite();
                } else {
                    System.out.println("Felaktig inmatning, kontrollera användaruppgifter!");

                    if (maxTry==0){
                        System.out.println("för många försök börja om\n");
                        break;
                    }
                }


            } while (!found);


        return found;
    }

    public static void cmdLogin() throws IOException {
        Console console = System.console();
        if (console == null) {
            System.out.println("Inte tillgängligt, Avluta och starta Kommandotolken\n");
            return;

        }
        boolean found = false;

        do {
            console.printf("Skriv användarnamn: ");
            String username = console.readLine();

            char[] passwordChars = console.readPassword("Skriv Lösenord: ");
            String password = new String(passwordChars);
            found = Admin.getUserInfo(username, password);
            if (found) {
                adminSite();
            } else {
                System.out.println("Felaktig inmatning, kontrollera användaruppgifter!");
            }
        } while (!found);

    }
    public static void adminSite() throws IOException {

        boolean exitProgram = false;
        do {
            System.out.println(ConsoleColor.CYAN);
            System.out.println("Välkommen, administratör!");
            System.out.println("Administration Frukt & Grönt vågen\n---------- HUVUDMENY ----------\nVad vill du göra?");
            System.out.println("0. Logga ut");
            System.out.println("1. Lägg till produkt");
            System.out.println("2. Lägg till kampanj");
            System.out.println("3. redigera kampanj");
            System.out.println("4. Redigera/Sök Ordinarie produkt");
            System.out.println("5. Väga produkt");
            System.out.println("6. Skapa ny administratör");
            System.out.println("Skriv Menysiffra");
            System.out.print("Ditt val: ");
            boolean choice = false;
            while (!choice) {
                try {
                    int menuChoice = input.nextInt();
                    input.nextLine();
                    choice = true;
                    switch (menuChoice) {
                        case 0 -> exitProgram = true;
                        case 1 -> addProduct();
                        case 2 -> addkampanj();
                        case 3 -> editKampanj();
                        case 4 -> editProduct();
                        case 5 -> weighProduct();
                        case 6 -> newAdmin();
                    }
                } catch (Exception e) {
                    System.out.print("Felaktig inmatning, försök igen\nSkriv menysiffra!: ");
                    input.next();
                }
            }
        } while (!exitProgram);
        System.out.println("Du är Utloggad!");
        System.out.println(ConsoleColor.RESET);

    }
    public static void addProduct() {
        boolean exitAddProduct = false;
        do {
            System.out.println("-- Lägger till produkt --\n(Skriv 0 närsomhelst för huvudmeny)");
            double priceInput = 0;
            System.out.println();
            System.out.print("Ange namnet på nya produkten: ");
            String nameInput;
            nameInput = checkIfBlankOrExit();
            if (nameInput.equals("0")) {
                break;
            }
            System.out.print("Ange pris på produkten (Endast siffror): ");
            boolean priceIn = false;
            while (!priceIn) {
                try {
                    priceInput = input.nextDouble();
                    input.nextLine();
                    priceIn = true;
                } catch (Exception e) {
                    System.out.println("Felaktig inmatning, använd kommatecken och Skriv en siffra");
                    input.next();
                }
            }
            System.out.print("Gör ett val:\n1. kg-pris\n2. st-pris");
            System.out.print("\nDitt val: ");
            String chosenUnit = checkIfBlankOrExit();
            String unit = null;
            if (chosenUnit.equals("1")){
                unit = "kg";
            } else if (chosenUnit.equals("2")) {
                unit = "st";
            } else {
                System.out.println("ogiltigt val börja om");
                addProduct();
            }
            System.out.println("Välj kategori");
            System.out.print("Gör ett val:\n1. Frukt\n2. Grönt ");
            System.out.print("\nDitt val: ");
            String categoryInput = null;
            String chosenCategory = checkIfBlankOrExit();
            if (chosenCategory.equals("1")){
                categoryInput = "Frukt";
            } else if (chosenCategory.equals("2")) {
                categoryInput = "Grönt";
            } else {
                System.out.println("ogiltigt val börja om");
                addProduct();
            }

            Product a = new Product(nameInput, priceInput, unit, categoryInput);
            listOfProducts.add(a);
            saveProductListToFile();
            System.out.println("Du har lagt till: " + a);
            exitAddProduct = true;

        } while (!exitAddProduct);
    }
    private static void saveOfferListToFile(){
        try {
            Path path = Paths.get("OfferList.txt");
            if(!Files.exists(path)){
                Files.createFile(path);
            }
            FileOutputStream fileOutputStream = new FileOutputStream("OfferList.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(offerList);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch (FileNotFoundException exp){
            System.err.println("File for saving products wasn't found");
        }catch (IOException exp){
            System.err.println("A error  has acured while writing to file");
        }
    }
    private static void readOfferListToFile(){
        try {
            Path path = Paths.get("OfferList.txt");
            if(!Files.exists(path)){
                System.err.println("there is no file with offers yet");
                Files.createFile(path);
                return;
            }
            FileInputStream fileInputStream = new FileInputStream("OfferList.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            offerList = (ArrayList<Offer>) objectInputStream.readObject();
            objectInputStream.close();
        }catch (FileNotFoundException exp){
            System.err.println("File for saving products wasn't found");
        }catch (IOException exp){
            System.err.println("A error  has acured while reading from file");
        }catch (ClassNotFoundException exp){
            System.err.println(exp.getMessage());
        }
    }

    private static void saveProductListToFile(){
        try {
            Path path =  Paths.get("ListOfProducts.txt");
            if(!Files.exists(path)){
                Files.createFile(path);
            }
            FileOutputStream fileOutputStream = new FileOutputStream("ListOfProducts.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listOfProducts);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch (FileNotFoundException exp){
            System.err.println("File for saving products wasn't found");
        }catch (IOException exp){
            System.err.println("A error  has acured while writing to file");
        }
    }
    private static void readProductListToFile(){
        try {
            Path path =  Paths.get("ListOfProducts.txt");
            if(!Files.exists(path)){
                System.err.println("there is no file with products yet");
                Files.createFile(path);
                return;
            }
            FileInputStream fileInputStream = new FileInputStream("ListOfProducts.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            listOfProducts = (ArrayList<Product>) objectInputStream.readObject();
            objectInputStream.close();
        }catch (FileNotFoundException exp){
            System.err.println("File for saving products wasn't found");
        }catch (IOException exp){
            System.err.println("A error  has acured while writing to file");
        }catch (ClassNotFoundException exp){
            System.err.println(exp.getMessage());
        }
    }
    public static void addkampanj() {
        System.out.println("\nNuvarande produkter");
        printAllProducts();
        double offerLimit = 0;
        String maxOrMini = null;
        Product foundPro = null;
        double foundProPrice = 0;
        double offerPrice = 0;
        String offerText = null;
        String foundProName = null;

        System.out.print("\nSkriv namnet på den du vill skapa kampanj för,\n" +
                "om den inte finns lägg till den som produkt först!\n\nDin Sökning: ");

        String searchQuest = checkIfBlankOrExit();

        for (int i = 0; i < listOfProducts.size(); i++){
            foundPro = listOfProducts.get(i);
            foundProName = listOfProducts.get(i).getName();
            foundProPrice = listOfProducts.get(i).getPrice();




            if (searchQuest.equalsIgnoreCase(foundProName)) {

                System.out.print("Ange en (MAX/MINST) gräns i siffror  för kampanjen (endast siffror): ");
                offerLimit = checkDouble();

                System.out.println("Ska det vara\n1.Max "+ offerLimit +"\n2.Minst "+ offerLimit);
                System.out.println("Ditt val: ");
                int limitChoice = checkInt();
                switch (limitChoice) {
                    case 1 -> maxOrMini = "max";
                    case 2 -> maxOrMini = "mini";
                }

                System.out.println("Skriv fritext (ex max 2kg/kund eller vid köp av minst 2st): ");
                offerText = checkIfBlankOrExit();

                System.out.print("Skriv nytt kampanjpris (endast siffror och punkt!): ");
                offerPrice = checkDouble();
                foundPro.setPrice(offerPrice);

                Offer a = new Offer(foundPro, offerPrice, foundProPrice, offerText,offerLimit, maxOrMini);
                offerList.add(a);
                saveOfferListToFile();

                showAllKampanj();

                break;

            }

        }

    }


    public static void newAdmin() throws IOException {
        System.out.print("Skriv användarnamn: ");
        String username = input.nextLine();
        System.out.print("Skriv Lösenord: ");
        String password = input.nextLine();
        Admin a = new Admin(username, password);
    }
    public static void showAllKampanj() {
        for (Offer p : offerList) {
            System.out.println("\u001B[32m" + p + "\u001B[0m");
        }
    }
    public static void costumerSite() throws FileNotFoundException {
        System.out.println(ConsoleColor.BLUE);
        boolean exitShop = false;
        System.out.println("Välkommen till matbutiken Kära Kund");
        System.out.println("Välj ett av nedanstående alternativ");
        Product found = null;
        String searchQuest = null;
        showAllKampanj();
        ShoppingCart shoppingCart1 = null;
        double sum = 0;
        double totalPrice = 0;
        double totalPrice1 = 0;
        double totalPrice2 = 0;
        double totalUnits = 0;

        do {
            System.out.println(ConsoleColor.BLUE);
            System.out.println("1. Handla Frukt\n2. Handla Grönt\n3. Fri sökning\n0. Avsluta och skriv ut kvitto");
            System.out.print("Välj din siffra: ");
            //String choice = checkIfBlankOrExit();
            int choice = checkInt();
            System.out.println("");
            if (choice==0) {
                if (shoppingItems.isEmpty()) {
                    System.out.println("inga produkter i varukorgen\nÅtergår till startsida..\n");
                    break;
                } else {
                    for (Product product : shoppingItems) {
                        sum += product.getPrice();
                    }
                    Receipt receipt = new Receipt(shoppingCart1);
                    for (Product p : shoppingItems){
                        shoppingItems.remove(p);
                        break;
                    }
                    break;
                }

            }
            switch(choice){
                case 1 -> searchQuest = "FRUKT";
                case 2 -> searchQuest = "GRÖNT";
                case 3 -> {
                    System.out.println("Ange fritext");
                    searchQuest = checkIfBlankOrExit();
                }

            }


            found = charSearchProduct(listOfProducts, searchQuest);
            System.out.println("\nSkriv '0' för att gå tillbaka");
            System.out.print("Skriv Produktnamn på önskad vara: ");
            String confirm = checkIfBlankOrExit();

            if (found != null) {
                String answer;
                boolean limit = true;
                for (Product chosenProduct : listOfProducts) {

                    if (confirm.equalsIgnoreCase(chosenProduct.getName())) {
                        System.out.println("du valde: " + chosenProduct.getName());

                        System.out.print("hur många "+ chosenProduct.getUnit() +"(endast siffror använd PUNKT INTE KOMMATECKEN): " );
                        if (chosenProduct.getUnit().equalsIgnoreCase("st")){
                            totalUnits = (int)totalUnits;
                            totalUnits = checkInt();
                        } else {
                            totalUnits = checkDouble();
                        }
                        for (Offer offerProduct : offerList){
                            if (chosenProduct.equals(offerProduct.getName())){
                                limit = checkLimit(totalUnits,offerProduct.getOfferLimit(),offerProduct.getMaxOrMini());
                                if (!limit){
                                    if (offerProduct.getMaxOrMini().equals("max")){
                                        System.out.println("Du får " + (int)offerProduct.getOfferLimit() + " " + offerProduct.getName().getUnit() + " till kampanjpris, resterande till ordinare pris.");
                                        totalPrice1 = calculatePrice(offerProduct.getOfferLimit(), offerProduct.getOfferPrice());
                                        totalPrice2 = calculatePrice(offerProduct.getRegularPrice(), (totalUnits - offerProduct.getOfferLimit()));
                                        totalPrice = (totalPrice1 + totalPrice2);
                                    }
                                    if (offerProduct.getMaxOrMini().equals("mini")){
                                        System.out.println(ConsoleColor.GREEN);
                                        System.out.println("om du köper " + offerProduct.getOfferLimit() + offerProduct.getName().getUnit() + " får du dom för " + offerProduct.getOfferPrice() + "kr/" + offerProduct.getName().getUnit());
                                        System.out.println("1. Fortsätta med erbjudandet");
                                        System.out.println("2. Fortsätta med Ordinare pris");
                                        System.out.println(ConsoleColor.RESET);
                                        System.out.print("Ditt val: ");
                                        String offerChoice = input.nextLine();
                                        if (offerChoice.equals("1")){
                                            totalPrice = calculatePrice(offerProduct.getOfferLimit(), offerProduct.getOfferPrice());
                                        }
                                        if (offerChoice.equals("2")){
                                            totalPrice = calculatePrice(offerProduct.getRegularPrice(), totalUnits);
                                        }
                                    }
                                }
                            }
                        }
                        while (limit){
                            totalPrice = calculatePrice(chosenProduct.getPrice(), totalUnits);
                            break;
                        }
                        System.out.printf("Vill du lägga till %.2f %s %s för %.2fkr?",totalUnits, chosenProduct.getUnit() , chosenProduct.getName(), totalPrice);
                        System.out.print("\n1. Ja\n2. Nej, gå tillbaka\n\u001B[34mDitt val: \u001B[0m");
                        answer = checkIfBlankOrExit();

                        if (answer.equals("1")) {

                            Product p1 = new Product(chosenProduct, (int)totalPrice, totalUnits, chosenProduct.getUnit());
                            shoppingItems.add(p1);

                        }
                        if (answer.equals("2")) {
                            break;
                        }

                    }

                }
            }
                    sum = 0;
                    for (int i = 0; i < shoppingItems.size(); i++) {

                        sum += shoppingItems.get(i).getPrice();
                        //for
                    }
                    shoppingCart1 = new ShoppingCart(sum, shoppingItems.size(), shoppingItems);
                    if (shoppingItems.isEmpty()){
                        System.out.println("Kundvagnen är tom");
                    } else {
                        System.out.println("Kundvagn\n" + shoppingItems);
                    }



        } while (!exitShop);
        System.out.println(ConsoleColor.RESET);

    }

    public static int checkInt(){
        boolean choice = false;
        int checkingInt = 0;
        while (!choice) {
            try {
                checkingInt = input.nextInt();
                input.nextLine();
                choice = true;
            } catch (Exception e) {
                System.out.print("Skriv en siffra (heltal)!: ");
                input.next();
            }
        }
        return checkingInt;
    }
    public static boolean checkLimit(double totalUnits,double offerLimit, String maxOrMini){
        boolean b = false;
        if (maxOrMini.equalsIgnoreCase("max")){
            if (totalUnits > offerLimit){
                b = false;
            } else {
                b = true;
            }
        }
        if (maxOrMini.equalsIgnoreCase("mini")){
            if (totalUnits < offerLimit){
                b = false;
            } else {
                b = true;
            }
        }

        return b;
    }
    public static double checkDouble() {
        boolean correctInput = false;
        double check;
        do {
            String kilosAsString = input.nextLine();
            String stringKiloWithoutLetters = kilosAsString.replaceAll("[a-zA-Z]", "");
            check = Double.parseDouble(stringKiloWithoutLetters);
            correctInput = true;

        } while (!correctInput);

        return check;
    }
    public static String checkIfBlankOrExit() {
        boolean checkInput = false;
        String productName = null;
        while (!checkInput) {
            productName = input.nextLine();
            if (productName.isBlank()) {
                System.out.println("Ange ett värde: ");
            } else {
                checkInput = true;
            }
            if (productName.equals("0")) {
                return "0";
            }
        }
        return productName;
    }
    public static void weighProduct() {
        boolean exitWeigh = false;
        do {
            Product found;
            printAllCategories();
            System.out.print("\nSök på KATEGORI eller PRODUKTNAMN: ");
            String searchQuest = checkIfBlankOrExit();
            if (searchQuest.equals("0")) {
                break;
            }
            found = charSearchProduct(listOfProducts, searchQuest);
            System.out.print("BEKRÄFTA PRODUKTNAMN: ");
            String userConfirm = input.nextLine();
            if (userConfirm.equals("0")) {
                break;
            }
            for (Product product : listOfProducts) {

                if (userConfirm.equalsIgnoreCase((product).getName())) {

                    System.out.println("Du valde: " + userConfirm);
                    System.out.print("Hur många kilo?: ");
                    boolean weight = false;
                    while (!weight) {
                        try {
                            double a = product.getPrice();
                            double b = input.nextDouble();
                            input.nextLine();
                            double c = calculatePrice(a, b);
                            System.out.println("Priset blir " + c + "Kr");
                            weight = true;

                        } catch (Exception e) {
                            System.out.print("felaktig inmatning, försök igen: \nHur många kilo?: ");
                            input.next();

                        }

                    }
                } else if (found == null) {
                    System.out.println("*** NOT FOUND, TRY AGAIN ***");
                    return;
                }
            }
            break;
        } while (!exitWeigh);
    }
    public static Product charSearchProduct(ArrayList<Product> listOfProducts, String searchQuest) {

        Product p = null;

        System.out.println("Hittade följande: ");
        for (Product pro : listOfProducts) {
            if (pro.getName().toLowerCase().contains(searchQuest.toLowerCase())) {
                System.out.println(pro);
                p = pro;
            } else if (pro.getCategory().toLowerCase().contains(searchQuest.toLowerCase())) {
                System.out.println(pro);
                p = pro;
            }
        }return p;
    }
    public static Offer charSearchOffer(ArrayList<Offer> offerList, String searchQuest){


            Offer o = null;

            System.out.println("Hittade följande: ");
            for (Offer offer : offerList) {
                if (offer.getName().getName().toLowerCase().contains(searchQuest.toLowerCase())) {
                    o = offer;
                }
            }return o;
    }
    public static void printAllCategories() {
        ArrayList<String> allUniqueCategories = new ArrayList<>();
        System.out.println("NUVARANDE KATEGORIER ");
        for (Product product : listOfProducts) {
            boolean productFound = false;
            for (String allUniqueCategory : allUniqueCategories) {
                if (allUniqueCategory.equalsIgnoreCase(product.getCategory())) {
                    productFound = true;
                    break;
                }
            }
            if (!productFound) {
                allUniqueCategories.add(product.getCategory());
            }
        }
        for (String product : allUniqueCategories) {
            System.out.println(product);
        }
    }
    public static void printAllProducts(){
        for (Product p : listOfProducts){
            System.out.println(p);
        }
    }
    public static double calculatePrice(double price, double inputKilo) {
        return price * inputKilo;
    }
    public static void editKampanj(){
        double newOfferPrice = 0;
        String whatToChange = null;
        String offerToChange;
        Offer found;
        String chosenOffer;
        showAllKampanj();
        System.out.println("Sök på kampanj du vill ändra: ");
        chosenOffer = checkIfBlankOrExit();
        found = charSearchOffer(offerList,chosenOffer);
        System.out.println(found);
        if (found!=null){
            System.out.println("BEKRÄFTA PRODUKT ATT ÄNDRA");
            offerToChange = checkIfBlankOrExit();
            for (int i = 0; i < offerList.size(); i++) {
                if (offerToChange.equals(offerList.get(i).getName().getName())){
                    System.out.println("Alternativ för " + offerList.get(i).getName());
                    System.out.println("1. Ändra pris");
                    System.out.println("2. Ta bort kampanj");

                }

                whatToChange = checkIfBlankOrExit();
                switch (whatToChange){
                    case "1" -> {
                        System.out.println("Skriv nytt kampanjpris");
                        newOfferPrice = checkDouble();
                        offerList.get(i).getName().setPrice(newOfferPrice);
                        saveOfferListToFile();
                        System.out.println("UPPDATERAD KAMPANJ" + offerList.get(i));
                    }
                    case "2" -> {
                        System.out.println("Du har nu tagit bort " + offerList.get(i).getName());
                        offerList.remove(i);
                        saveOfferListToFile();

                    }
                }
            }
        }

    }
    public static void editProduct() throws IOException {
        boolean exitEdit = false;
        Product found;
        String userConfirm;
        do {
            System.out.println("\n-- REDIGERA/SÖK PRODUKT --\n(Skriv 0 närsomhelst för huvudmeny)");
            printAllCategories();
            System.out.println();
            //vad händer om sökt produkt inte finns i lista???

            System.out.println("Sök på KATEGORI eller PRODUKTNAMN: ");
            String userSearch = checkIfBlankOrExit();
            if (userSearch.equals("0")) {
                adminSite();
            }
            found = charSearchProduct(listOfProducts, userSearch);



            if (found != null) {
                boolean foundIt = false;
                do {
                    System.out.print("BEKRÄFTA PRODUKTNAMN FÖR REDIGERING: ");
                    userConfirm = checkIfBlankOrExit();
                    if (userConfirm.equals("0")) {
                        foundIt = true;
                    }
                    for (int i = 0; i < listOfProducts.size(); i++) {
                        if (userConfirm.equalsIgnoreCase(listOfProducts.get(i).getName())) {
                            System.out.println("Alternativ för: " + listOfProducts.get(i));
                            foundIt = true;
                        }  else {
                            System.out.println("Hittade inget");
                            editProduct();
                        }


                    }
                } while (!foundIt);

                //en till metod för att väga???
                System.out.println("1. Ändra pris ");
                System.out.println("2. Ändra namn");
                System.out.println("3. Radera");
                System.out.println("0. Återgå till Huvudmeny");
                System.out.println("Ditt val: ");

                int userChoice = input.nextInt();
                input.nextLine();
                switch (userChoice) {
                    case 0 -> exitEdit = true;
                    case 1 -> changePrice(userConfirm);
                    case 2 -> changeName(userConfirm);
                    case 3 -> deleteProduct(userConfirm);
                }

            } else {
                System.out.println("Hittade inget. Sök på tex 'GRÖNT' eller 'Banan'");
                editProduct();
            }
            exitEdit = true;
        } while (!exitEdit);
    }
    public static void changePrice(String chosenProduct) {

        for (Product listOfProduct : listOfProducts) {
            if (chosenProduct.equalsIgnoreCase(listOfProduct.getName())) {
                System.out.print("Skriv nytt pris: ");
                double newPrice = 0;
                boolean newInput = false;
                while (!newInput) {
                    try {
                        newPrice = input.nextDouble();
                        newInput = true;
                    } catch (Exception e) {
                        System.out.println("Ange siffror använd kommatecken! ");
                        input.next();
                    }
                }
                listOfProduct.setPrice(newPrice);
                System.out.println("Priset för " + chosenProduct + " har ändrats till " + newPrice + "kr");
                saveProductListToFile();
            }
        }
    }
    public static void changeName(String chosenProduct) {
        for (Product listOfProduct : listOfProducts) {
            if (chosenProduct.equalsIgnoreCase(listOfProduct.getName())) {
                System.out.print("(tryck 0 för huvudmeny)\nSkriv nytt namn: ");
                String newName = checkIfBlankOrExit();
                if (newName.equals("0")) {
                    break;
                }
                listOfProduct.setName(newName);
                System.out.println("Namnet för " + chosenProduct + " har ändrats till " + newName);
            }
            break;
        }
    }
    public static void deleteProduct(String chosenProduct) {
        for (int i = 0; i < listOfProducts.size(); i++) {
            boolean answer = false;
            if (chosenProduct.equalsIgnoreCase(listOfProducts.get(i).getName())) {
                while (!answer) {
                    System.out.println("Är du säker att du vill radera: " + chosenProduct);
                    System.out.println("1. JA\n2. NEJ, GÅ TILLBAKA");
                    System.out.print("DITT VAL: ");
                    try {
                        int answerChoice = input.nextInt();
                        input.nextLine();
                        if (answerChoice == 1) {
                            listOfProducts.remove(i);
                            System.out.println("Du har nu raderat: " + chosenProduct);
                            answer = true;
                        }
                        if (answerChoice == 2) {
                            System.out.println("GÅR TILLBAKA..");
                            answer = true;
                        }
                    } catch (Exception e) {
                        System.out.print("felaktig inmatning, försök igen\n");
                        input.next();
                    }
                }
                break;
            }
        }
    }
    public static void addProductsForTesting(){
        listOfProducts.add(new Product("Äpple", 23.90,"kg", "FRUKT"));
        listOfProducts.add(new Product("Apelsin", 26.90,"kg", "FRUKT"));
        listOfProducts.add(new Product("Banan", 25.90,"kg", "FRUKT"));
        listOfProducts.add(new Product("Avocado", 19.90,"st", "FRUKT"));
        listOfProducts.add(new Product("Citron", 10,"st", "GRÖNT"));
        listOfProducts.add(new Product("Morot", 10,"kg", "GRÖNT"));
        listOfProducts.add(new Product("Gurka", 15,"st", "GRÖNT"));
        listOfProducts.add(new Product("Sallad", 15,"st", "GRÖNT"));
        listOfProducts.add(new Product("Tomat", 29.90,"kg", "GRÖNT"));

        for (int i = 0; i < listOfProducts.size(); i++) {
            Product foundProduct = listOfProducts.get(i);
            if ("Banan".equals(foundProduct.getName())){
                foundProduct.setPrice(15);
                offerList.add(new Offer(foundProduct, 15, 25, "max 5kg per kund",5,"max" ));
            }
            if ("Citron".equals(foundProduct.getName())){
                foundProduct.setPrice(15);
                offerList.add(new Offer(foundProduct, 5, 10, "vid köp av minst 2 st",2,"mini" ));
            }
            if ("Morot".equals(foundProduct.getName())){
                foundProduct.setPrice(5);
                offerList.add(new Offer(foundProduct, 5, 10, "vid köp av minst 2 kg",2,"mini" ));
            }
            if ("Apelsin".equals(foundProduct.getName())){
                foundProduct.setPrice(15);
                offerList.add(new Offer(foundProduct, 15, 26.90, "max 2kg per kund",2,"max" ));
            }

        }
    }
}
