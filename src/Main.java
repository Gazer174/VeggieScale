import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static ArrayList<Product> listOfProducts = new ArrayList<Product>();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean exitProgram = false;
        System.out.println("Startar program...\nProgram startat\nVÄLKOMMEN");
        do {
            System.out.println("Administration Frukt & Grönt vågen\n---------- HUVUDMENY ----------\nVad vill du göra?");
            System.out.println("0. Avsluta program");
            System.out.println("1. Lägg till produkt");
            System.out.println("2. Redigera/Sök produkt");
            System.out.println("3. Väga produkt");
            System.out.println("Skriv Menysiffra");
            System.out.print("Ditt val: ");
            boolean choice = false;
            while (!choice) {
                try {
                    int menuChoice = input.nextInt();
                    input.nextLine();
                    choice = true;
                    switch (menuChoice){
                        case 0 -> exitProgram = true;
                        case 1 -> addProduct();
                        case 2 -> editProduct();
                        case 3 -> weighProduct();
                    }
                } catch (Exception e) {
                    System.out.print("felaktig inmatning, försök igen: \nSkriv menysiffra!: ");
                    input.next();
                }
            }
        } while (!exitProgram);
        System.out.println("Avslutar program...\nHA EN FORTSATT TREVLIG DAG!");
        System.out.println("Program avslutat");
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
            if (nameInput.equals("0")){
                break;
            }
            System.out.print("Ange pris på produkten (Endast siffror): ");
            boolean priceIn = false;
            while (!priceIn) {
            try {
                priceInput = input.nextDouble();
                input.nextLine();
                priceIn = true;
            } catch (Exception e){
                System.out.println("Felaktig inmatning, använd kommatecken och Skriv en siffra");
                input.next();
                }
            }
            System.out.print("Ange produkt kategori (Frukt eller Grönt?): ");
            String categoryInput = checkIfBlankOrExit();
            if (categoryInput.equals("0")){
                break;
            }

            System.out.print("Ange underkategori(ex. äpplen, paprika, stenfrukter): ");
            String subCategory = checkIfBlankOrExit();
            if (subCategory.equals("0")){
                break;
            }

            Product a = new Product(nameInput, priceInput, new String[] {categoryInput,subCategory});
            listOfProducts.add(a);
            System.out.println("Du har lagt till: " + a);
            exitAddProduct = true;

        } while (!exitAddProduct);

    }
    public static String checkIfBlankOrExit() {
        boolean checkInput = false;
        String productName = null;
        while (!checkInput) {
            productName = input.nextLine();
            if (productName.isBlank()) {
                System.out.println("Ange ett namn: ");
            } else {
                checkInput = true;
            }
            if (productName.equals("0")){
                System.out.println("återgår till huvudmeny..");
                return "0";
            }
        }
        return productName;
    }
    public static void weighProduct(){
        boolean exitWeigh = false;
        do {
            Product found;
            printAllCategories();
            System.out.print("\nSök på KATEGORI eller PRODUKTNAMN: ");
            String searchQuest = checkIfBlankOrExit();
            if (searchQuest.equals("0")){
                break;
            }
            found = charSearchProduct(listOfProducts, searchQuest);
            System.out.print("BEKRÄFTA PRODUKTNAMN: ");
            String userConfirm = input.nextLine();
            if (userConfirm.equals("0")){
                break;
            }
            for (Product product : listOfProducts) {

                if (userConfirm.equals((product).getName())){

                    System.out.println("Du valde: " + userConfirm);
                    System.out.print("Hur många kilo?: ");
                    boolean weight = false;
                    while (!weight) {
                        try {
                            double a = product.getPrice();
                            double b = input.nextDouble();
                            input.nextLine();
                            calculatePrice(a,b);
                            weight = true;

                        } catch (Exception e) {
                            System.out.print("felaktig inmatning, försök igen: \nHur många kilo?: ");
                            input.next();

                        }

                    }
                }else if (found==null) {
                    System.out.println("*** NOT FOUND, TRY AGAIN ***");
                    return;
                }
            }
        } while (!exitWeigh);
    }

    public static Product charSearchProduct(ArrayList<Product> listOfProducts, String searchQuest){
        Product p = null;
        System.out.println("Hittade följande: ");
        for (Product pro : listOfProducts) {
            if (pro.getName().toLowerCase().contains(searchQuest.toLowerCase())){
                System.out.println(pro);
                p = pro;
            } else if (pro.getCategory().toLowerCase().contains(searchQuest.toLowerCase())) {
                System.out.println(pro);
                p = pro;
            }
        }
        return p;
    }
    public static void printAllCategories(){
        ArrayList<String> allUniqueCategories = new ArrayList<>();
        System.out.println("NUVARANDE KATEGORIER ");
        for (Product product : listOfProducts) {
            boolean productFound = false;
            for (String allUniqueCategory : allUniqueCategories) {
                if (allUniqueCategory.equals(product.getCategory())) {
                    productFound = true;
                    break;
                }
            }
            if (!productFound){
                allUniqueCategories.add(product.getCategory());
            }
        }
        for (String product : allUniqueCategories){
                System.out.println(product);
        }
    }
    public static void calculatePrice(double price, double inputKilo){
        double sum = price * inputKilo;
        System.out.println("Priset blir: " + sum + "kr");
    }
    public static void editProduct(){
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
                if (userSearch.equals("0")){
                    break;
                }
                found = charSearchProduct(listOfProducts, userSearch);
                System.out.println("BEKRÄFTA PRODUKTNAMN FÖR REDIGERING: ");
                userConfirm = checkIfBlankOrExit();
                if (userConfirm.equals("0")){
                    break;
                }
            if (found!=null) {

                    System.out.println("ALTERNATIV för: " + userConfirm);
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
                System.out.println("*** NOT FOUND, TRY AGAIN ***");
            }
            exitEdit = true;
        } while (!exitEdit);
    }
    public static void changePrice(String chosenProduct){

            for (Product listOfProduct : listOfProducts) {
                if (chosenProduct.equals(listOfProduct.getName())) {
                    System.out.print("Skriv nytt pris: ");
                    double newPrice = 0;
                    boolean newInput = false;
                    while (!newInput) {
                        try {
                            newPrice = input.nextDouble();
                            newInput = true;
                        } catch (Exception e) {
                            System.out.println("Ange siffror! ");
                            input.next();
                        }
                    }
                    listOfProduct.setPrice(newPrice);
                    System.out.println("Priset för " + chosenProduct + " har ändrats till " + newPrice + "kr");

                }
            }

    }
    public static void changeName(String chosenProduct){
        for (Product listOfProduct : listOfProducts) {
            if (chosenProduct.equals(listOfProduct.getName())) {
                System.out.print("(tryck 0 för huvudmeny)\nSkriv nytt namn: ");
                String newName = checkIfBlankOrExit();
                if (newName.equals("0")){
                    break;
                }
                listOfProduct.setName(newName);
                System.out.println("Namnet för " + chosenProduct + " har ändrats till " + newName);


            }
            break;
        }
    }
    public static void deleteProduct(String chosenProduct){
        for (int i = 0; i < listOfProducts.size(); i++) {
            boolean answer = false;
            if (chosenProduct.equals(listOfProducts.get(i).getName())){
                while (!answer) {
                    System.out.println("Är du säker att du vill radera: " + chosenProduct);
                System.out.println("1. JA\n2. NEJ, GÅ TILLBAKA");
                System.out.print("DITT VAL: ");
                    try {
                        int answerChoice = input.nextInt();
                        input.nextLine();
                        if (answerChoice == 1){
                            listOfProducts.remove(i);
                            System.out.println("Du har nu raderat: " + chosenProduct);
                            answer = true;
                        }
                        if (answerChoice == 2){
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
}