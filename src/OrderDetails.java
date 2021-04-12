import java.util.Date;

/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 10:56 AM
 * Project DatabasG07
 */
public class OrderDetails {
    protected int id;
    protected int ordersId;
    protected int productId;

    public OrderDetails() {}

    public OrderDetails(int id, int ordersId, int productId) {
        this.id = id;
        this.ordersId = ordersId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
