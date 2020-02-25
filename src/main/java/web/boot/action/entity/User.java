package web.boot.action.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author z
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public static final Map<Integer, User> USERS = new HashMap<>();

    static {
        USERS.put(1, new User(1, "叔宝", "秦", "13800138001"));
        USERS.put(2, new User(2, "叔宝", "秦", "13800138002"));
        USERS.put(3, new User(3, "叔宝", "秦", "13800138003"));
        USERS.put(4, new User(4, "叔宝", "秦", "13800138004"));
        USERS.put(5, new User(5, "叔宝", "秦", "13800138005"));
        USERS.put(6, new User(6, "叔宝", "秦", "13800138006"));
        USERS.put(7, new User(7, "叔宝", "秦", "13800138007"));
        USERS.put(8, new User(8, "叔宝", "秦", "13800138008"));
        USERS.put(9, new User(9, "叔宝", "秦", "13800138009"));
        USERS.put(10, new User(10, "叔宝", "秦", "13800138010"));
        USERS.put(11, new User(11, "叔宝", "秦", "13800138011"));
    }

    public User() {

    }

    public User(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone);
    }
}
