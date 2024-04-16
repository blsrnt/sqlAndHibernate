import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }
}
