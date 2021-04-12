import javax.xml.crypto.Data;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 10:29 AM
 * Project DatabasG07
 */
public class Orders {
    protected int id;
    protected int customerId;
    protected Date ordersDate;
    protected double cost;
    protected int status;
    protected int addressId;

    public Orders() {}

    public Orders(int id, int customerId, Date ordersDate, double cost, int status, int addressId) {
        this.id = id;
        this.customerId = customerId;
        this.ordersDate = ordersDate;
        this.cost = cost;
        this.status = status;
        this.addressId = addressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
