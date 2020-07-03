import model.Board;
import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class SequenceMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("learn-jpa");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    /* 비즈니스 로직 함수
    *  em : EntityManager
    * */
    public static void logic(EntityManager em) {
        // 멤버 생성

        for(int i = 0; i < 5; i ++ ) {
            Board board = new Board();
            /* Sequence 를 이용한 자동 키 매핑을 통해 ID 값이 자동으로 증가한다.
             * */
            board.setData("Board" + i);
            em.persist(board);
        }

        // Board 목록 조회
        List<Board> boards = em.createQuery("select b from Board b", Board.class)
                .getResultList();

        for (Board board : boards) {
            System.out.println(board.toString());
        }
    }
}
