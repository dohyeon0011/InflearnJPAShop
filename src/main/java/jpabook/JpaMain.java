package jpabook;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {

    public static void main(String[] args) {
        // persistence.xml의 unit name을 넘겨주기
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 웹 서버가 올라오는 시점에 DB당 하나만 생성
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 꺼내기(자바의 컬렉션이라 생각하면 됨. 객체를 저장해주는), 고객의 요청이 올 때마다 썼다가 버리고 쓰고(쓰레드간에 공유X)

        // JPA에서는 트랜잭션 단위가 가장 중요
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = new Order();
            order.addOrderItem(new OrderItem());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
