import model.Member;
import model.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = em.find(Member.class, 1L);

        Period period = new Period(member.getWorkPeriod().getStartDate(),
                member.getWorkPeriod().getEndDate());

        Member member2 = new Member();
        member2.setWorkPeriod(period);

        System.out.println(member.getWorkPeriod().getStartDate());
        System.out.println(member.getWorkPeriod().getEndDate());


        tx.commit();

        em.close();
        emf.close();
    }
}
