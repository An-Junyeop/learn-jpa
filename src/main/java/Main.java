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

        for(int i = 1; i <= 10; i ++) {
            Member member = new Member();
            member.setName("member_" + i);
            em.persist(member);
        }

        String jpql = "select m from Member as m where m.name = 'member_2'";
        List<Member> resultList = em
                .createQuery(jpql, Member.class)
                .getResultList();

        for (Member member : resultList) {
            System.out.println("member.id = " + member.getId() + ", member.name = " + member.getName());
        }

        tx.commit();

        em.close();
        emf.close();
    }
}
