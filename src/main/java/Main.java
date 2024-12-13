
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UserDao userDao = new UserDao();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nUser Management Console");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. View All Users");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createUser(scanner);
                case 2 -> readUser(scanner);
                case 3 -> updateUser(scanner);
                case 4 -> deleteUser(scanner);
                case 5 -> viewAllUsers();
                case 6 -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void createUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, email, password);
        userDao.create(user);
        System.out.println("User created with ID: " + user.getId());
    }

    private static void readUser(Scanner scanner) {
        System.out.print("Enter user ID to read: ");
        int userId = scanner.nextInt();

        User user = userDao.read(userId);
        if (user != null) {
            System.out.println("User ID: " + user.getId());
            System.out.println("Username: " + user.getUserName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser(Scanner scanner) {
        System.out.print("Enter user ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User user = userDao.read(userId);
        if (user != null) {
            System.out.print("Enter new username: ");
            String username = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();

            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            userDao.update(user);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("Enter user ID to delete: ");
        int userId = scanner.nextInt();

        userDao.delete(userId);
        System.out.println("User deleted successfully (if existed).");
    }

    private static void viewAllUsers() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Username: " + user.getUserName() + ", Email: " + user.getEmail());
            }
        }
    }
}
