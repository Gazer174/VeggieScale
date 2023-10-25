import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Admin {
    private String name;
    private String username;
    private String password;
    private static int nUsers = 1;
    public Admin(String name, String username, String password) throws IOException {
        this.name = name;
        this.username = username;
        this.password = password;
        createAdmin(this.name,this.username,this.password);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
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



    public void createAdmin(String name, String username, String password)throws IOException {
        PrintWriter p = new PrintWriter(new FileWriter("Login.txt",true));
        p.println("Användare & nr: " + this.name + " " + nUsers++ +"\nAnvändarnamn: " + this.username + " \nLösenord: " + this.password);
        p.println();
        p.close();
    }


}
