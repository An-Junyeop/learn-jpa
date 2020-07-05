import model.Member;
import model.MemberProduct;
import model.MemberProductId;
import model.Product;

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

            //save(em, member1, product, 3);
            find(em, 1, 1);
            find(em, 1, 2);
            find(em, 1, 4);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void find(EntityManager em, int memberId, int productId) {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember(memberId);
        memberProductId.setProduct(productId);

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

        System.out.println("member.name = " + memberProduct.getMember().getName());
        System.out.println("product.name = " + memberProduct.getProduct().getName());
        System.out.println("OrderAmount = " + memberProduct.getOrderAmount());
    }

    private static void save(EntityManager em, Member member, Product product, int orderAmount) {
        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member);
        memberProduct.setProduct(product);
        memberProduct.setOrderAmount(orderAmount);

        em.persist(memberProduct);
    }


}
