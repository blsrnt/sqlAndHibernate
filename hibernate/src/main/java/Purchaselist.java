import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class Purchaselist {
    @EmbeddedId
    private PurchaselistKey id;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public PurchaselistKey getId() {
        return id;
    }

    public void setId(PurchaselistKey id) {
        this.id = id;
    }
}
