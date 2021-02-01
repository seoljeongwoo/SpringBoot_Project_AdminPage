package com.example.study.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;



    // Item 1 : N OrderDatail
    // FetchType = LAZY : 지연로딩 , EAGER : 즉시로딩
    // LAZY = SELECT * FROM item where id = ?
    // 연관 관계를 고려하지 않겠다.

    // EAGER = 연관 관계가 설정된 모든 테이블에 대해서 Join 이 일어남
    // 성능의 저하의 위험, EAGER = 1:1 또는 1건만 존재할시 추천하는 Fetch Type
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // where item.id = ?
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    //private List<OrderDetail> orderDetailList;

}
