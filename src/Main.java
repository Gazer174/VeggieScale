import java.util.ArrayList;
import java.util.Scanner;
//höhhööhööh
public class Main {
    public static ArrayList<Product> listOfProducts = new ArrayList<Product>();

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Product p1 = new Product("grönt äpple", 10,  new String[]{"frukt","äpplen"});
        Product p2 = new Product("kålrot", 15,  new String[]{"grönt","rotfrukter"});
        Product p3 = new Product("morot", 14,  new String[]{"grönt","rotfrukter"});
        Product p4 = new Product("pepparrot", 17, new String[]{"grönt","rotfrukter"});
        Product p5 = new Product("rött äpple", 13, new String[]{"frukt", "äpplen"});
        listOfProducts.add(p1);
        listOfProducts.add(p2);
        listOfProducts.add(p3);
        listOfProducts.add(p4);
        listOfProducts.add(p5);



        boolean exitProgram = false;
        System.out.println("Startar program...\nVÄLKOMMEN");
        do {
            System.out.println("Administrationen\n---------- HUVUDMENY ----------\nVad vill du göra?");
            System.out.println("0. Avsluta program");
            System.out.println("1. Lägg till produkt");
            //System.out.println("2. Söka efter en produkt");
            System.out.println("3. Redigera produkt");
            System.out.println("4. Väga produkt");
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
                        //case 2 -> navigateProduct();
                        case 3 -> editProduct();
                        case 4 -> weighProduct();
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
    /*public static void navigateProduct(){
        String chosenCateg;
        printAllCategories();
        System.out.println("Välj kategori att navigera i: ");
        checkIfBlank();
        System.out.println("Skriv produktnamn: ");
        checkIfBlank();

    }

     */
    public static void addProduct() {
            double priceInput = 0;
            System.out.print("Ange namnet på nya produkten: ");
            String nameInput;
            nameInput= checkIfBlank();

            System.out.print("Ange pris på produkten (Endast siffror): ");
            boolean priceIn = false;
            while (!priceIn) {
            try {
                priceInput = input.nextDouble();
                input.nextLine();
                priceIn = true;
            } catch (Exception e){
                System.out.println("felaktig inmatning, använd kommatecken och Skriv en siffra");
                input.next();
                }
            }
            //lägg till enhetspris!!!

            System.out.print("Ange produkt kategori (Frukt eller Grönt?): ");
            String categoryInput = checkIfBlank();


            System.out.print("ange underkategori(ex. äpplen, paprika, stenfrukter): ");
            String subCategory = checkIfBlank();


            Product a = new Product(nameInput, priceInput, new String[] {categoryInput,subCategory});
            listOfProducts.add(a);
            System.out.println("Du har lagt till: " + a);

    }
    public static String checkIfBlank() {
        boolean checkInput = false;
        String productName = null;
        while (!checkInput) {
            productName = input.nextLine();
            if (productName.isBlank()) {
                System.out.println("Ange ett namn: ");
            }else {
                checkInput = true;
            }

        }

        return productName;
    }
    public static void weighProduct(){
        Product found;
        printAllCategories();
        System.out.print("\nSök på KATEGORI eller PRODUKTNAMN: ");
        String searchQuest = input.nextLine();
        found = charSearchProduct(listOfProducts, searchQuest);
        System.out.print("BEKRÄFTA PRODUKTNAMN: ");
        String choice = input.nextLine();
        for (Product product : listOfProducts) {

            if (choice.equals((product).getName())){

                    System.out.println("Du valde: " + choice);
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






    }
    /*public static double checkInput(){
        System.out.print("Antal Kilo?: ");
        boolean success = false;
        while (!success){
            try{
                double num = input.nextDouble();
                System.out.println(num);
                success = true;

            }
            catch (Exception e){
                System.out.println("felaktig inmatning, försök igen: ");
                input.nextDouble();

            }
        }
        return 0;
    }

     */

    public static Product charSearchProduct(ArrayList<Product> listOfProducts, String searchQuest){
        Product p = null;
        for (Product pro : listOfProducts) {
            if (pro.getName().toLowerCase().contains(searchQuest.toLowerCase())){
                System.out.println("Hittade: " + pro);
                p = pro;

            } else if (pro.getCategory().toLowerCase().contains(searchQuest.toLowerCase())) {


                System.out.println("Hittade: " + pro);
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



            Product found;
            System.out.println("Administrationen\n--- REDIGERA PRODUKT ---\n");
            printAllCategories();
            System.out.println();
//vad händer om sökt produkt inte finns i lista???
            System.out.println("(tryck 0 för huvudmeny)\nSök på KATEGORI eller PRODUKTNAMN: ");
            String nextChoice = checkIfBlank();
            found = charSearchProduct(listOfProducts, nextChoice);
            String[] array;
            System.out.println("(tryck 0 för huvudmeny)\nBEKRÄFTA PRODUKTNAMN: ");
            String chosenOne = input.nextLine();



            if (found!=null) {
                boolean exitToMainMenu = false;
                array = new String[]{chosenOne};
                do {
                    System.out.println("ALTERNATIV för: " + array[0]);
                    //en till metod för att väga???
                    System.out.println("1. Ändra pris ");
                    System.out.println("2. Ändra namn");
                    System.out.println("3. Radera");
                    System.out.println("0. Återgå till Huvudmeny");
                    System.out.println("Ditt val: ");

                    int userChoice = input.nextInt();
                    input.nextLine();
                    switch (userChoice) {
                        case 0 -> exitToMainMenu = true;
                        case 1 -> changePrice(array[0]);
                        case 2 -> changeName(array[0]);
                        case 3 -> deleteProduct(array[0]);
                    }
                    break;
                } while (!exitToMainMenu);
            } else {
                System.out.println("*** NOT FOUND, TRY AGAIN ***");
            }

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
                break;
            }

        }
    }
    public static void changeName(String chosenProduct){
        for (Product listOfProduct : listOfProducts) {
            if (chosenProduct.equals(listOfProduct.getName())) {
                System.out.print("Skriv nytt namn: ");
                String newName = input.nextLine();
                listOfProduct.setName(newName);
                System.out.println("Namnet för " + chosenProduct + " har ändrats till " + newName);

                break;

            }

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