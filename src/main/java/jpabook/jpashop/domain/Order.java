package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // DB에 ORDER가 예약어로 걸려 있어서
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /*@Column(name = "MEMBER_ID")
    private Long memberId;*/

    // 외래키가 주인
    @ManyToOne(fetch = FetchType.LAZY) // 단방향
    @JoinColumn(name = "MEMBER_ID") // 쓰지 않으면 조인용 테이블로 중간 테이블이 하나 더 추가돼서 운영에도 어려움이 있음.
    private Member member;

    // 배송과 주문은 일대일 관계
    // 지연로딩, order를 생성해서 오더를 저장하면 자동으로 딜리버리도 저장
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    // 양방향 (값만 가져오기 용)
    // 다대다를 중간 테이블인 orderItem을 만들어 일대다로 풀어냄
    // 연관관계의 자식 클래스에 cascade 걸기
    // order를 생성해서 오더를 저장(persist)를 하면 자동으로 orderItems도 저장
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 일대다 상황에서 역방향으로 읽기 전용으로 쓰려고 할 때인데 야매 방법임.
    /*@ManyToOne
    @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
    private Member member*/

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
