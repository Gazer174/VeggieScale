import java.io.*;
import java.util.Scanner;

public class Admin {
    private String username;
    private String password;


    public Admin(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        createAdmin(this.username,this.password);
    }
    public static boolean getUserInfo(String username, String password) throws FileNotFoundException {
        String path = "C:\\Users\\ITHS\\IdeaProjects\\VeggieScale\\Login.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);
        BufferedReader bufferedReader;

        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            boolean userExist = false;
            while ((line = bufferedReader.readLine()) != null){
                if (line.contains("Användarnamn: " + username)&&line.contains("Lösenord: " + password)){
                    if (username.isBlank()){
                        break;
                    }
                    if (password.isBlank()){
                        break;
                    }
                    else {
                        userExist = true;
                    }
                }
            }
            return userExist;
        } catch (IOException e) {
            System.out.println("Hittade inget!!!" + e);
            throw new RuntimeException(e);
        }
    }


    public String getUsername() {
        return this.username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

   public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public void createAdmin(String username, String password)throws IOException {
        String path = "C:\\Users\\ITHS\\IdeaProjects\\VeggieScale\\Login.txt";
        PrintWriter p = new PrintWriter(new FileWriter(path,true));
        p.println("\nAnvändarnamn: " + this.username + " Lösenord: " + this.password);
        p.println();
        p.close();
    }


}
