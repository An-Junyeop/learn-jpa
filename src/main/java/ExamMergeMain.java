import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");

    public static void main(String[] args) {
        Member member = createMember("memberA", "회원1");

        member.setName("회원명변경"); // 준영속 상태에서 유저의 값을 변경

        mergeMember(member);
    }

    static Member createMember(String id, String username) {
        /* 영속성 컨텍스트1 시작 */
        EntityManager  em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();

        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setName(username);
        member.setAge(25);

        em1.persist(member);
        tx1.commit();

        /*
        * 영속성 컨텍스트1 종료
        * member 엔티니는 준영속 상태가 됨
        * */
        em1.close();

        return member;
    }

    static void mergeMember(Member member) {
        /* 영속성 컨텍스트2 시작 */
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);

        // 비영속 엔티티 생성
        Member newMember = new Member();
        newMember.setName("병합할회원");
        newMember.setAge(20);

        /*merge 메소드를 이용할 경우 영속성 컨텍스트를 조회하고,
        * 없을 경우 데이터베이스에서 조회한다
        * 데이터베이스에 없을 경우 새로 생성한다. (insert)
        * */
        newMember = em2.merge(newMember);

        // 영속 상태의 엔티티이기 때문에 수정이 가능
        newMember.setName("변경된이름");
        tx2.commit();

        // 준영속 상태의 member
        System.out.println("준영속 상태 " + member.toString());
        // merge 메소드를 이용하여 영속 상태의 엔티티는 mergeMember 이기 때문에 member 엔티티는 준영속 상태 = false
        System.out.println("em2 contains member = " + em2.contains(member));

        // 영속 상태의 mergeMember
        System.out.println("영속 상태 " + mergeMember.toString());
        // merge 메소드를 이용하여 영속 상태의 엔티티를 반환 받은 mergeMember 엔티티는 영속 상태 = true
        System.out.println("em2 contains mergeMember = " + em2.contains(mergeMember));

        // merge 메소드를 이용한 영속 상태의 newMember
        System.out.println(newMember.toString());
        // merge 메소드를 이용하여 영속 상태로 만든 후 같은 newMember에 반환 받음
        System.out.println("em2 contains newMember = " + em2.contains(newMember));


        em2.close();
    }
}
