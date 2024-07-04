package com.example.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupon")
@Entity
public class Coupon extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private CouponType couponType;

  private Integer totalQuantity;

  @Column(nullable = false)
  private int issuedQuantity;

  @Column(nullable = false)
  private int discountAmount;

  @Column(nullable = false)
  private int minAvailableAmount;

  @Column(nullable = false)
  private LocalDateTime dateIssueStart;

  @Column(nullable = false)
  private LocalDateTime dateIssueEnd;

}