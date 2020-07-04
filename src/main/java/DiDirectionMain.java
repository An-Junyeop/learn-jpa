import model.Member;
import model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DiDirectionMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            diDirectiom(em);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void diDirectiom(EntityManager em) {
        // Team 1
        Team team1 = new Team();
        team1.setName("team1");
        em.persist(team1);

        // Member1
        Member member1 = new Member();
        member1.setUsername("member1");

        // 양방향 연관관계 설정
        member1.setTeam(team1); // member1 -> team1을
        team1.getMembers().add(member1); // team1 -> member1
                                         // 순수한 객체를 고려하여 team1의 members에 member1을 추가
        em.persist(member1);

        // Member2
        Member member2 = new Member();
        member2.setUsername("member2");

        // 양방향 연관관계 설정(위와 동일)
        member2.setTeam(team1);
        team1.getMembers().add(member2);
        em.persist(member2);

        Team findTeam = em.find(Team.class, team1.getId());

        System.out.println(findTeam);
        for(Member member : findTeam.getMembers()) {
            System.out.println(member.toString() + ", " + member.getTeam().toString());
        }

    }

}
