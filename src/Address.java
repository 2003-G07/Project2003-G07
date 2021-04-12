/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 10:21 AM
 * Project DatabasG07
 */
public class Address {
    protected int id;
    protected String cityName;
    protected String address;
    protected String zipcode;

    public Address() {}

    public Address(int id, String cityName, String address, String zipcode) {
        this.id = id;
        this.cityName = cityName;
        this.address = address;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
