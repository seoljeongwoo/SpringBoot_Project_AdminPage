package com.example.study.model.Entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    // Item 1 : N OrderDetail

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;

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
