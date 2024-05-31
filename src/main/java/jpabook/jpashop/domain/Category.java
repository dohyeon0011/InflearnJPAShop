package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 셀프 매핑
    // 상위 카테고리
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // parent와 셀프로 잡힘.
    // 하위 카테고리
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", // CATEGORY_ITEM 이라는 중간 조인 테이블 잡기
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), // 중간 테이블이 있다 치고 내가 조인 하는 것은 얘고
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")) // 반대쪽이 조인 해야 하는것은 얘
    private List<Item> items = new ArrayList<>();


}
