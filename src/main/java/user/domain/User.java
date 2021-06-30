package user.domain;

public final class User {
    private final String id;
    private final String name;
    private final String password;

    public User(final String id, final String name, final String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public final String id() {
        return id;
    }

    public final String name() {
        return name;
    }

    public final String password() {
        return password;
    }
}