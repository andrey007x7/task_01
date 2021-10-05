package task01.services;

import task01.entities.Role;
import task01.entities.User;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class UserServiceImp implements UserService {
    private List<User> userList;

    @Override
    public void addNewUser() {
        System.out.println("Please enter Name, Surname, email, role(s), phone(s)");
        System.out.println("In that format: Andrei, Shkurko, andrei@mail.com, [USER & Admin], [375254563212 & 375145984875]");
        User user = new User();
        if (addInfo(user)) {
            userList = readUserList();
            userList.add(user);
            writeUserList(userList);
            System.out.println("Done!");
        } else {
            System.out.println("You entered something incorrect please try again");
            addNewUser();
        }
    }

    @Override
    public boolean editUser(int index) {
        userList = readUserList();
        System.out.println("Please enter with changes");
        User user = new User();
        if (index > 0 && userList.size() >= index) {
            System.out.println("In that format: " + userList.get(index - 1).getName()
                    + ", " + userList.get(index - 1).getSurname()
                    + ", " + userList.get(index - 1).getEmail()
                    + ", " + getCorrectFormatFromList(userList.get(index - 1).getRoles())
                    + ", " + getCorrectFormatFromList(userList.get(index - 1).getPhones()));
            if (addInfo(user)) {
                userList.set(index - 1, user);
                System.out.println("Done!");
            } else {
                System.out.println("You entered something incorrect please try again");
                editUser(index);
            }
            userList.set(index - 1, user);
            writeUserList(userList);
        } else {
            System.out.println("Please enter the correct number of the user in the list");
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(int index) {
        userList = readUserList();
        if (index > 0 && userList.size() >= index) {
            userList.remove(index - 1);
            writeUserList(userList);
            System.out.println("Done!");
        } else {
            System.out.println("Please enter the correct number of the user in the list");
            return true;
        }
        return false;
    }

    @Override
    public boolean showUserInfo(int index) {
        userList = readUserList();
        if (index > 0 && userList.size() >= index) {
            System.out.println(userList.get(index - 1));
        } else {
            System.out.println("Please enter the number of user in the list");
            return true;
        }
        return false;
    }

    @Override
    public boolean showAllUsers() {
        userList = readUserList();
        if (userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                System.out.printf("%d. %s %s, email: %s\n", i + 1, userList.get(i).getName(),
                        userList.get(i).getSurname(), userList.get(i).getEmail());
            }
        } else {
            System.out.println("The list of users is empty");
            return false;
        }
        return true;
    }

    private void writeUserList(List<User> userList) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("users.bin", false);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean addInfo(User user) {
        Scanner scanner = new Scanner(System.in);
        String[] userInfo = scanner.nextLine().split(", ");
        Pattern patternForEmail = Pattern.compile("\\S+[@]\\S+[.]\\S+");
        user.setName(userInfo[0]);
        user.setSurname(userInfo[1]);
        if (patternForEmail.matcher(userInfo[2]).matches()) {
            user.setEmail(userInfo[2]);
        } else {
            return false;
        }
        if (addRoles(userInfo[3]) != null) {
            user.setRoles(addRoles(userInfo[3]));
        } else return false;
        if (addPhones(userInfo[4]) != null) {
            user.setPhones(addPhones(userInfo[4]));
        } else return false;
        return true;
    }

    private List<String> addPhones(String infoOfPhones) {
        String[] phones = infoOfPhones.substring(1, infoOfPhones.length() - 1).toUpperCase().split(" & ");
        List<String> userPhones = new ArrayList<>();
        Pattern patternForPhone = Pattern.compile("375[0-9]{9}");
        if (phones.length <= 3 && phones.length > 0) {
            for (int i = 0; i < phones.length; i++) {
                if (patternForPhone.matcher(phones[i]).matches()) {
                    userPhones.add(phones[i]);
                }
            }
        } else return null;
        return userPhones;
    }

    private List<Role> addRoles(String infoOfRoles) {
        String[] roles = infoOfRoles.substring(1, infoOfRoles.length() - 1).toUpperCase().split(" & ");
        List<Role> userRoles = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            userRoles.add(Role.valueOf(roles[i]));
        }
        if (userRoles.size() == 0 || userRoles.size() > 2) {
            return null;
        }
        if (userRoles.size() == 2) {
            if (userRoles.get(0).getAccessLevel() == userRoles.get(1).getAccessLevel()) {
                return null;
            }
            if ((userRoles.get(0).getAccessLevel() == 3 || userRoles.get(1).getAccessLevel() == 3)) {
                return null;
            }
        }
        return userRoles;
    }

    private List<User> readUserList() {
        List<User> userList = new ArrayList<>();
        File file = new File("users.bin");
        if (file.length() != 0) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                userList = (List<User>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    private String getCorrectFormatFromList(List<?> list) {
        String objectLine = "[";
        for (int i = 0; i < list.size(); i++) {
            objectLine += list.get(i);
            if (i + 1 != list.size()) {
                objectLine += " & ";
            } else {
                objectLine += "]";
            }
        }
        return objectLine;
    }

}
