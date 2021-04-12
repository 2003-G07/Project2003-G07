/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 11:10 AM
 * Project DatabasG07
 */
public class Admin {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String tel;
    protected String email;

    public Admin() {}

    public Admin(int id, String firstName, String lastName, String tel, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
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
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
