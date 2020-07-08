import model.*;

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

            // logic
/*
//            saveParent(em);
            Parent findParent = findParent(em, 1);
            System.out.println("parent.name = " + findParent.getName());

//            saveChild(em, findParent, 1);
//            saveChild(em, findParent, 2);

            List<Child> children = findChildren(em, findParent);

            for(Child child : children) {
                System.out.println("find child.name = " + child.getName());
            }

            System.out.println("findChild.name = " + findChild.getName());
            System.out.println("findChild.parent.id = " + findChild.getParent().getId());

//            saveGrandChild(em, findChild, 1);
//            saveGrandChild(em, findChild, 2);
//            saveGrandChild(em, findChild, 3);
*/

            Child findChild = findChild(em, 1, 1);
            /*List<GrandChild> grandChildren = findGrandChildren(em, findChild);

            for(GrandChild grandChild : grandChildren) {
                System.out.println("grandChild.name = " + grandChild.getName());
            }*/
            GrandChild grandChild = findGrandChild(em, findChild, 1);
            System.out.println(grandChild.getName());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static GrandChild findGrandChild(EntityManager em, Child findChild, int id) {
        GrandChildId grandChildId = new GrandChildId();
        ChildId childId = new ChildId();
        childId.setChildId(findChild.getChildId());
        childId.setParent(findChild.getParent().getId());

        grandChildId.setChildId(childId);
        grandChildId.setId(id);
        return em.find(GrandChild.class, grandChildId);
    }

    private static Child findChild(EntityManager em, int parentId, int childId) {
        ChildId childIdObj = new ChildId(parentId, childId);
        return em.find(Child.class, childIdObj);
    }

    public static void saveParent(EntityManager em) {
        Parent parent = new Parent();
        parent.setId(1);
        parent.setName("parent1");

        em.persist(parent);
    }

    public static Parent findParent(EntityManager em, int id) {
        return em.find(Parent.class, id);
    }

    public static void saveChild(EntityManager em, Parent parent, int id) {
        Child child = new Child();
        child.setChildId(id);
        child.setParent(parent);
        child.setName("child" + id);

        em.persist(child);
    }

    public static List<Child> findChildren(EntityManager em, Parent parent) {
        return em
                .createQuery("select c from Child c where c.parent = :parent")
                .setParameter("parent", parent)
                .getResultList();
    }

    public static void saveGrandChild(EntityManager em, Child child, int grandChildId) {
        GrandChild grandChild = new GrandChild();
        grandChild.setId(grandChildId);
        grandChild.setChild(child);
        grandChild.setName("grand_child" + grandChildId);

        em.persist(grandChild);
    }

    public static List<GrandChild> findGrandChildren(EntityManager em, Child child) {
        return em
                .createQuery("select gc from GrandChild gc where gc.child = :child")
                .setParameter("child", child)
                .getResultList();
    }


}