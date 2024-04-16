import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }
}
