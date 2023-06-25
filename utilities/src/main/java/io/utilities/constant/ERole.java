package io.utilities.constant;

public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMIN("ROLE_ADMIN");
    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
