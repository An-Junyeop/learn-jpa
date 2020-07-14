import model.Member;

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

        tx.begin();

        // 네이티브 SQL
        String sql = "select id, name from MEMBER where name = 'member_2'";
        List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();



        for(Member member : resultList) {
            System.out.println(member.toString());
        }

        tx.commit();

        em.close();
        emf.close();
    }
}
