package task01.entities;

public enum Role {
    USER(1), CUSTOMER(1), ADMIN(2), PROVIDER(2), SUPER_ADMIN(3);

    private final int accessLevel;

    Role(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
