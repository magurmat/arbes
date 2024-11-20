package com.phonecompany.billing.service;

import java.math.BigDecimal;
public interface TelephoneBillCalculator {

  /**
   * Calculates the total cost of telephone calls based on a given phone log.
   *
   * <p>The method processes a string input representing multiple call records in CSV format.
   * Each record includes a phone number, the start time, and the end time of a call.
   * The cost of each call is calculated based on the following rules:
   *
   * <ul>
   *   <li>Minutes during the interval <b>08:00:00 to 16:00:00</b> are charged at <b>1.00 Kč</b> per started minute.
   *   <li>Minutes outside this interval are charged at a reduced rate of <b>0.50 Kč</b> per started minute.
   *   <li>For calls longer than five minutes, all additional minutes beyond the first five
   *       are charged at a reduced rate of <b>0.20 Kč</b> per started minute.
   *   <li>The most frequently called phone number in the log (based on the number of calls)
   *       is excluded from billing as part of a promotional offer.
   *       <ul>
   *         <li>If there is a tie in frequency, the phone number with the numerically highest value is chosen as the free number.</li>
   *       </ul>
   * </ul>
   *
   * <p><b>Input Format:</b> The input string must follow this format:
   * <pre>
   * phoneNumber,startTime,endTime
   * phoneNumber,startTime,endTime
   * ...
   * </pre>
   * <b>Example:</b>
   * <pre>
   * 420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57
   * 420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
   * </pre>
   *
   * <p><b>Output:</b> The method returns the total cost of all billed calls as a {@code BigDecimal}.
   *
   * @param phoneLog A string representing multiple call records in CSV format.
   *                 Each line represents one call and contains:
   *                 <ul>
   *                   <li>The phone number (e.g., "420774577453").</li>
   *                   <li>The start time of the call in the format "dd-MM-yyyy HH:mm:ss".</li>
   *                   <li>The end time of the call in the same format.</li>
   *                 </ul>
   * @return A {@code BigDecimal} representing the total cost of all billed calls.
   *         Calls to the most frequently called number are excluded from billing.
   * @throws IllegalArgumentException If the input string is invalid or cannot be parsed.
   */
  BigDecimal calculate (String phoneLog);
}