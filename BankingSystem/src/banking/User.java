package banking;

public class User {
    protected int id;
    protected String name;
    protected String passwordHash;



    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.passwordHash = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }


}
