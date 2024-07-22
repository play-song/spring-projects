package com.example.core.repository.mysql;

import com.example.core.domain.CouponIssue;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.core.domain.QCouponIssue.couponIssue;

@RequiredArgsConstructor
@Repository
public class CouponIssueRepository {
  private final JPAQueryFactory queryFactory;

  public CouponIssue findByFirst(long userId, long couponId) {
    return queryFactory.selectFrom(couponIssue)
        .where(couponIssue.couponId.eq(couponId))
        .where(couponIssue.userId.eq(userId))
        .fetchFirst();
  }
}
