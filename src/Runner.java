import task01.services.UserService;
import task01.services.UserServiceImp;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        getMenu();
    }

    private static void getMenu() {
        UserService userService = new UserServiceImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please chose: \n[1 - add new user] [2 - show all users] [3 - exit] ");
        switch (scanner.next()) {
            case "1":
                userService.addNewUser();
                getMenu();
                break;
            case "2":
                if (userService.showAllUsers()) {
                    getAllUsersMenu(userService, scanner);
                }
                getMenu();
                break;
            case "3":
                break;
            default:
                System.out.println("Please enter correct");
                getMenu();
                break;
        }
    }

    private static void getAllUsersMenu(UserService userService, Scanner scanner) {
        System.out.println("Please chose: \n[1 - show more information] [2 - edit user] " +
                "[3 - remove user] [4 - back] ");
        int index;
        switch (scanner.next()) {
            case "1":
                System.out.println("Enter the number of user in the list");
                index = scanner.nextInt();
                while (userService.showUserInfo(index)) {
                    index = scanner.nextInt();
                }
                break;
            case "2":
                System.out.println("Enter the number of the user in the list");
                index = scanner.nextInt();
                while (userService.editUser(index)) {
                    index = scanner.nextInt();
                }
                break;
            case "3":
                System.out.println("Enter the number of the user in the list");
                index = scanner.nextInt();
                while (userService.removeUser(index)) {
                    index = scanner.nextInt();
                }
                break;
            case "4":
                break;
            default:
                System.out.println("Please enter correct next time:)");
                break;
        }
    }
}
