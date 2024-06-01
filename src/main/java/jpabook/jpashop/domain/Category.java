package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 셀프 매핑
    // 상위 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // parent와 셀프로 잡힘.
    // 하위 카테고리
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /**
     * 다대다 매핑(실무는 복잡해서 일대다와 다대다로 풀어내는게 좋음)
     * MEMBER 테이블과 PRODUCT 테이블 사이에 조인용 테이블(member_id(pk, fk), product(pk, fk)이 생김.)
     * 단방향
     * 실무에서 사용X, 연결 테이블이 단순히 연결만 하고 끝나지 않고, 주문시간, 수량과 같은 데이터가 들어올 수 있음.
     * 쿼리도 중간 테이블이 숨겨져 있기 때문에 이상하게 나감.
     * @ManyToMany -> @OneToMany, @ManyToOne으로 바꿔서 하기
     */
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", // CATEGORY_ITEM 이라는 중간 조인 테이블 잡기
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), // 중간 테이블이 있다 치고 내가 조인 하는 것은 얘고
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")) // 반대쪽이 조인 해야 하는것은 얘
    private List<Item> items = new ArrayList<>();


}
