package com.example.study.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //order_detail
//@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // OrderDetail N : 1 User ,연관관계 설정시 객체 타입 설정필수!!
    // 연관관계 설정 변수에 대해서는 ToString Annotation에서 제외시켜야함
    // User 와 Item 이 ToString() 재정의가 되어있기 때문에 Stack overflow 발생
    //@ManyToOne
    //private User user;  // user_id

    // OrderDetail N : 1 Item
    //@ManyToOne
    //private Item item;
}
