/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 11:07 AM
 * Project DatabasG07
 */
public class ProductCategory {
    protected int id;
    protected int produktId;
    protected int categoryId;

    public ProductCategory(){}

    public ProductCategory(int id, int produktId, int categoryId) {
        this.id = id;
        this.produktId = produktId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
