package com.phonecompany.billing.model;

import java.time.LocalDateTime;

public record CallRecord(String phoneNumber, LocalDateTime startTime, LocalDateTime endTime) {

  @Override
  public String toString() {
    return "CallRecord{" +
        "phoneNumber='" + phoneNumber + '\'' +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        '}';
  }
}