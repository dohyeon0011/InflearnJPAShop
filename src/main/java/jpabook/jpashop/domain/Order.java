package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 일대다 단방향 매핑 단점
 * 엔티티가 관리하는 외래 키가 다른 테이블에 있음.
 * 연관관계 관리를 위해 추가로 UPDATE SQL 실행
 * 웬만하면 다대일 양방향 매핑 사용(단방향이 일단 최우선)
 */
@Entity
@Table(name = "ORDERS") // DB에 ORDER가 예약어로 걸려 있어서
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /*@Column(name = "MEMBER_ID")
    private Long memberId;*/

    // 외래키가 주인
    @ManyToOne // 단방향
    @JoinColumn(name = "MEMBER_ID") // 쓰지 않으면 조인용 테이블로 중간 테이블이 하나 더 추가돼서 운영에도 어려움이 있음.
    private Member member;

    // 양방향 (값만 가져오기 용)
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 일대다 상황에서 역방향으로 읽기 전용으로 쓰려고 할 때인데 야매 방법임.
    /*@ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private OrderItem orderItem;*/

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // OrderItem과 양방향 관계를 가지면서 값 집어넣기
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
