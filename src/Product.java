import java.util.Date;

/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 11:04 AM
 * Project DatabasG07
 */
public class Product {
    protected int id;
    protected String name;
    protected int quantity;
    protected String imgLink;
    protected String description;
    protected int price;

    public Product() {}

    public Product(int id, String name, int quantity, String imgLink, String description, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imgLink = imgLink;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
