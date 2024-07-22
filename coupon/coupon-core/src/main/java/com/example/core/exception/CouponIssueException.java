package com.example.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CouponIssueException extends RuntimeException {
  private final ErrorCode ErrorCode;
  private final String message;

  @Override
  public String getMessage() {
    return "[%s] %s".formatted(ErrorCode, message);
  }
}
