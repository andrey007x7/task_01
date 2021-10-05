package task01.entities;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String surname;
    private String email;
    private List<Role> roles;
    private List<String> phones;

    public User() {
    }

    public User(String name, String surname, String email, List<Role> roles, List<String> phones) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return name + ", " + surname + ", email: " + email + ", roles: " + roles + ", phones: " + phones;
    }

}
