package model;

public class User {
    private String username;
    private String password;
    private String cellphoneNo;
    private String email;
    private String name;
    private String address;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String username, String password, String cellphoneNo, String email, String name, String address) {
        this.username = username;
        this.password = password;
        this.cellphoneNo = cellphoneNo;
        this.email = email;
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", cellphoneNo=" + cellphoneNo + ", email=" + email + ", name=" + name + ", address=" + address + "]";
    }
}
