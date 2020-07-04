import model.Member;
import model.Team;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("learn-jpa");


        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        init(em);
        queryLogicJoin(em);
        updateRelation(em);

        tx.commit();
        em.close();
        emf.close();
    }

    private static void queryLogicJoin(EntityManager em) {
        // teamName 파라미터를 받아 팀의 회원을 조회하는 JPQL
        String jpql = "select m from Member m join m.team t where " +
                "t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "Team_1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println(member.toString());
        }
    }

    private static void init(EntityManager em) {
        // 팀 저장
        Team team = new Team();
        team.setName("Team_1");
        em.persist(team);

        // 회원 3명 저장
        for (int i = 0; i < 3; i ++) {
            Member member = new Member();
            member.setUsername("Member_" + i);
            member.setTeam(team);
            em.persist(member);
            System.out.println("member_" + i + ".team = " + member.getTeam().toString());
        }
    }

    private static void updateRelation(EntityManager em) {
        // 새로운 팀
        Team team = new Team();
        team.setName("Team_2");
        em.persist(team);

        // Member_1 회원을 조회하여 새로 생성한 팀으로 수정
        Member findMember = em.find(Member.class, Long.valueOf(3));
        findMember.setTeam(team);

        System.out.println(findMember.toString());

        // Member_2 회원을 조회하여 소속된 팀이 없도록 수정
        Member findMember2 = em.find(Member.class, Long.valueOf(4));
        findMember2.setTeam(null);

        System.out.println(findMember2.toString());
    }
}
