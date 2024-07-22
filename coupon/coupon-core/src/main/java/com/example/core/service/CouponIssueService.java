package com.example.core.service;

import com.example.core.domain.Coupon;
import com.example.core.domain.CouponIssue;
import com.example.core.exception.CouponIssueException;
import com.example.core.repository.mysql.CouponIssueJpaRepository;
import com.example.core.repository.mysql.CouponIssueRepository;
import com.example.core.repository.mysql.CouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.core.exception.ErrorCode.DUPLICATED_COUPON_ISSUE;
import static com.example.core.exception.ErrorCode.NOT_FOUND_COUPON;


@RequiredArgsConstructor
@Service
public class CouponIssueService {
  private final CouponIssueJpaRepository couponIssueJpaRepository;
  private final CouponJpaRepository couponJpaRepository;
  private final CouponIssueRepository couponIssueRepository;

  @Transactional
  public void issue(long couponId, long userId) {
    Coupon coupon = findCoupon(couponId);
    coupon.issue();
    saveCouponIssue(couponId, userId);
  }

  @Transactional(readOnly = true)
  public Coupon findCoupon(long couponId) {
    return couponJpaRepository.findById(couponId)
        .orElseThrow(() -> new CouponIssueException(NOT_FOUND_COUPON, "쿠폰이 존재하지 않습니다. %s".formatted(couponId)));
  }

  @Transactional(readOnly = true)
  public CouponIssue saveCouponIssue(long couponId, long userId) {
    checkAlreadyIssuance(couponId, userId);
    return couponIssueJpaRepository.save(
        CouponIssue.builder()
            .couponId(couponId)
            .userId(userId)
            .build()
    );
  }

  public void checkAlreadyIssuance(long couponId, long userId) {
    CouponIssue couponIssue = couponIssueRepository.findByFirst(userId, couponId);
    if (couponIssue != null) {
      throw new CouponIssueException(DUPLICATED_COUPON_ISSUE, "이미 발급된 쿠폰입니다. couponId : %s, userId : %s".formatted(couponId, userId));
    }
  }

}
