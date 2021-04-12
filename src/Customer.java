/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 10:27 AM
 * Project DatabasG07
 */
public class Customer {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String tel;
    protected String email;
    protected int addressId;

    public Customer() {}

    public Customer(int id, String firstName, String lastName, String tel, String email, int addressId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.addressId = addressId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
