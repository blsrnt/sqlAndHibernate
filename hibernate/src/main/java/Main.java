import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchaselist> query = builder.createQuery(Purchaselist.class);
        Root<Purchaselist> root = query.from(Purchaselist.class);
        query.select(root);
        List<Purchaselist> purchases = session.createQuery(query).getResultList();

        Session sessionsubscriptions = sessionFactory.openSession();
        CriteriaBuilder buildersubscriptions = sessionsubscriptions.getCriteriaBuilder();
        CriteriaQuery<Subscription> querysubscriptions = buildersubscriptions.createQuery(Subscription.class);
        Root<Subscription> rootsubscriptions = querysubscriptions.from(Subscription.class);
        querysubscriptions.select(rootsubscriptions);
        List<Subscription> subscriptions = sessionsubscriptions.createQuery(querysubscriptions).getResultList();

        Transaction transaction = session.beginTransaction();
        for (Purchaselist purchaselist : purchases) {
            LinkedPurchaseListKey key = new LinkedPurchaseListKey();
            for (Subscription subscription : subscriptions) {

                if (session.get(Course.class, subscription.getId().getCourseId()).getName().equals(purchaselist.getId().getCourseName())
                && session.get(Student.class, subscription.getId().getStudentId()).getName().equals(purchaselist.getId().getStudentName())) {

                    key.setCourseId(subscription.getId().getCourseId());
                    key.setStudentId(subscription.getId().getStudentId());
                    LinkedPurchaseList list = new LinkedPurchaseList();
                    list.setId(key);
                    session.persist(list);

                }
            }
        }
        transaction.commit();
        sessionFactory.close();
    }
}
