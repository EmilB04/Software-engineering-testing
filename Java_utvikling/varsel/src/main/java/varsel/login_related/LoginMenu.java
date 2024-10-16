package varsel.login_related;


import java.util.HashMap;
import java.util.Scanner;

public class LoginMenu {
    private HashMap<String, String> users = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private Register register;
    private Login login;
    private static final String FILE_NAME = "users.txt";

    public LoginMenu() {
        // Opprett FileHandler-objekt
        FileHandler fileHandler = new FileHandler(FILE_NAME);

        // Last inn brukere fra filen
        users = fileHandler.loadUsers();

        // Opprett Register og Login-objekter med riktige parametere
        register = new Register(users, fileHandler);  // Riktig kall til konstruktør
        login = new Login(users);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register.registerUser();
                    break;
                case "2":
                    login.loginUser();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


}
