package com.example.core.domain;

import com.example.core.exception.CouponIssueException;
import com.example.core.exception.ErrorCode;
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

  public boolean availableIssueQuantity() {
    if (totalQuantity == null) {
      // 발급수량 무제한
      return true;
    }

    return totalQuantity > issuedQuantity;
  }

  public boolean availableIssueDate() {
    LocalDateTime now = LocalDateTime.now();
    return dateIssueStart.isBefore(now) && dateIssueEnd.isAfter(now);
  }

  public void issue() {
    if (availableIssueDate()) {
      throw new CouponIssueException(ErrorCode.INVALID_COUPON_ISSUE_DATE, "쿠폰을 발급받을 수 없습니다.");
    }

    if (availableIssueQuantity()) {
      throw new CouponIssueException(ErrorCode.INVALID_COUPON_ISSUE_QUANTITY, "발급 수량이 초과했습니다.");
    }

    issuedQuantity++;
  }

}