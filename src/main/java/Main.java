import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import model.Item;
import model.QItem;
import org.h2.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQuery query = new JPAQuery(em);

            String name = "시골개발자";
            Integer price = 10000;

            QItem item = QItem.item;

            BooleanBuilder builder = new BooleanBuilder();

            if (!"".equals(name) && name != null) {
                builder.and(item.name.contains(name));
            }
            if (price != null) {
                builder.or(item.price.gt(price));
            }

            List<Item> result = query.from(item)
                    .where(builder)
                    .list(item);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
