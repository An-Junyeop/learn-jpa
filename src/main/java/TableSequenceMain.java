import model.Board;
import model.Device;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TableSequenceMain {
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

    public static void logic(EntityManager em) {
        for(int i = 0; i < 5; i ++ ) {
            Device device = new Device();
            /* Table에 값을 저장(next_val 컬럼)하여 자동 키 매핑을 통해 ID 값이 자동으로 증가한다.
             * */
            device.setData("Device" + i);
            em.persist(device);
        }

        // Board 목록 조회
        List<Device> devices = em.createQuery("select d from Device d", Device.class)
                .getResultList();

        for (Device device : devices) {
            System.out.println(device.toString());
        }
    }
}
