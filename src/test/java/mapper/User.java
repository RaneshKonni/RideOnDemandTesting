package mapper;

public class User {
    private String fullName;
    private String email;
    private String password;
    private String mobile;
    private String city;
    private String shopName;
    private Role role;

    public User(){}

    public User(String fullName, String email, String password, String mobile, String city, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.city = city;
        this.role = role;
    }

    public User(String fullName, String email, String password, String mobile, String city, Role role, String shopName) {
        this(fullName, email, password, mobile, city, role);
        this.shopName = shopName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
