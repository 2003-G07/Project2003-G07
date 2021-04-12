/**
 * Created by Iryna Gnatenko
 * Date 2021-04-12
 * Time 11:09 AM
 * Project DatabasG07
 */
public class Category {
    protected int id;
    protected String categoryName;

    public Category() {}

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
