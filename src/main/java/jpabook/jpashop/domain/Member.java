package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 일대다 단방향 매핑 단점
 * 엔티티가 관리하는 외래 키가 다른 테이블에 있음.
 * 연관관계 관리를 위해 추가로 UPDATE SQL 실행
 * 웬만하면 다대일 양방향 매핑 사용(단방향이 일단 최우선)
 */
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    // 일대다
    /*@OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<Order> orders = new ArrayList<>();*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
