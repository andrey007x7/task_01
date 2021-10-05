package task01.services;

public interface UserService {

    void addNewUser();

    boolean editUser(int index);

    boolean removeUser(int index);

    boolean showUserInfo(int index);

    boolean showAllUsers();

}
