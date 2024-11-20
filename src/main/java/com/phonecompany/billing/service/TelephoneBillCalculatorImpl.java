package com.phonecompany.billing.service;

import com.phonecompany.billing.model.CallRecord;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  private static final LocalTime DAY_START = LocalTime.of(8, 0);
  private static final LocalTime DAY_END = LocalTime.of(16, 0);


  @Override
  public BigDecimal calculate(String phoneLog) {
    List<CallRecord> callRecords = parsePhoneLog(phoneLog);

    String freeNumber = getMostFrequentlyCalledNumber(callRecords);

    BigDecimal totalCost = BigDecimal.ZERO;

    for (CallRecord record : callRecords) {
      if (!record.phoneNumber().equals(freeNumber)) {
        totalCost = totalCost.add(calculateCallCost(record));
      }
    }

    return totalCost;
  }

  private List<CallRecord> parsePhoneLog(String phoneLog) {
    List<CallRecord> callRecords = new ArrayList<>();

    String[] lines = phoneLog.split("\n");

    for (String line : lines) {
      String[] parts = line.split(",");
      if (parts.length != 3) {
        throw new IllegalArgumentException("Invalid record: " + line);
      }

      String number = parts[0].trim();
      LocalDateTime start = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER);
      LocalDateTime end = LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMATTER);

      callRecords.add(new CallRecord(number, start, end));
    }

    return callRecords;
  }

  private BigDecimal calculateCallCost(CallRecord record) {
    BigDecimal cost = BigDecimal.ZERO;

    LocalDateTime start = record.startTime();
    LocalDateTime end = record.endTime();

    long totalMinutes = java.time.Duration.between(start, end).toMinutes();
    if (!start.toLocalTime().equals(end.toLocalTime())) {
      totalMinutes++; // Include the last partial minute
    }

    for (int i = 0; i < totalMinutes; i++) {
      LocalTime currentMinuteTime = start.plusMinutes(i).toLocalTime();

      if (i < 5) {
        // < 5 minutes
        if (isWithinDayRate(currentMinuteTime)) {
          cost = cost.add(new BigDecimal("1.0"));
        } else {
          cost = cost.add(new BigDecimal("0.5"));
        }
      } else {
        // > 5 minutes
        cost = cost.add(new BigDecimal("0.2"));
      }
    }

    return cost;
  }

  private boolean isWithinDayRate(LocalTime time) {
    return !time.isBefore(DAY_START) && time.isBefore(DAY_END);
  }

  private String getMostFrequentlyCalledNumber(List<CallRecord> callRecords) {
    Map<String, Long> frequencyMap = callRecords.stream()
        .collect(Collectors.groupingBy(CallRecord::phoneNumber, Collectors.counting()));

    // Find the most frequent number, using the highest numeric value as a tie-breaker
    String mostFrequentNumber = null;
    long maxFrequency = 0;

    for (Map.Entry<String, Long> entry : frequencyMap.entrySet()) {
      String number = entry.getKey();
      long frequency = entry.getValue();

      if (frequency > maxFrequency || (frequency == maxFrequency && (mostFrequentNumber == null || number.compareTo(mostFrequentNumber) > 0))) {
        mostFrequentNumber = number;
        maxFrequency = frequency;
      }
    }

    return mostFrequentNumber;
  }
}
