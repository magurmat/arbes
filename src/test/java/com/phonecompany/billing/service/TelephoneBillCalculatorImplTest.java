package com.phonecompany.billing.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelephoneBillCalculatorImplTest {

  TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();

  @Test
  void testCalculateWithFirstExample() {
    String input = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
        "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00";

    BigDecimal result = calculator.calculate(input);

    assertEquals(new BigDecimal("1.5"), result);
  }

  @Test
  void testCalculateWithFreeNumberPromo() {
    String input = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
        "420774577453,13-01-2020 18:20:15,13-01-2020 18:25:57\n" +
        "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00";

    BigDecimal result = calculator.calculate(input);

    assertEquals(new BigDecimal("6.2"), result);
  }

  @Test
  void testCalculateWithFreeNumberPromoAndChangingTariffs() {
    String input = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
        "420774577453,13-01-2020 18:20:15,13-01-2020 18:25:57\n" +
        "420776562353,18-01-2020 07:59:20,18-01-2020 08:10:00";

    BigDecimal result = calculator.calculate(input);


    assertEquals(new BigDecimal("5.7"), result);
  }

  @Test
  void testCalculateWithRandomPhoneLogs() {
    String input = "420734624100,15-01-2020 16:24:24,15-01-2020 23:48:12\n"
        + "420708013048,15-01-2020 04:35:44,15-01-2020 06:04:51\n"
        + "420713048005,15-01-2020 14:34:40,15-01-2020 20:42:54\n"
        + "420796218298,15-01-2020 04:54:29,15-01-2020 17:51:35\n"
        + "420780998845,15-01-2020 03:49:47,15-01-2020 14:06:46\n"
        + "420753517234,15-01-2020 06:54:41,15-01-2020 16:06:15\n"
        + "420787963285,15-01-2020 09:29:14,15-01-2020 18:10:07\n"
        + "420775804808,15-01-2020 02:41:50,15-01-2020 16:09:28\n"
        + "420756853686,15-01-2020 04:23:23,15-01-2020 09:13:41\n"
        + "420765905017,15-01-2020 07:41:21,15-01-2020 09:58:18\n"
        + "420793471864,15-01-2020 12:29:50,15-01-2020 17:14:59\n"
        + "420702136227,15-01-2020 23:02:21,15-01-2020 23:08:01\n"
        + "420763917227,15-01-2020 02:27:15,15-01-2020 22:29:23\n"
        + "420751746356,15-01-2020 19:31:06,15-01-2020 22:14:25\n"
        + "420777043077,15-01-2020 13:01:36,15-01-2020 21:17:49\n"
        + "420799227499,15-01-2020 02:04:59,15-01-2020 21:56:13\n"
        + "420776761334,15-01-2020 09:27:49,15-01-2020 15:30:51\n"
        + "420735686269,15-01-2020 01:32:15,15-01-2020 14:56:32\n"
        + "420709141081,15-01-2020 22:21:52,15-01-2020 23:35:11\n"
        + "420732810574,15-01-2020 05:02:03,15-01-2020 08:34:32\n"
        + "420721573493,15-01-2020 17:26:51,15-01-2020 20:16:56\n"
        + "420771631690,15-01-2020 14:14:50,15-01-2020 19:56:46\n"
        + "420720913906,15-01-2020 10:21:19,15-01-2020 10:32:16\n"
        + "420754536459,15-01-2020 03:45:59,15-01-2020 04:56:06\n"
        + "420700720640,15-01-2020 14:17:51,15-01-2020 23:44:13\n"
        + "420793960023,15-01-2020 10:45:51,15-01-2020 17:59:25\n"
        + "420748191876,15-01-2020 00:50:42,15-01-2020 15:00:26\n"
        + "420755170146,15-01-2020 04:30:50,15-01-2020 06:05:49\n"
        + "420766346191,15-01-2020 19:51:17,15-01-2020 20:28:24\n"
        + "420759613988,15-01-2020 16:22:05,15-01-2020 21:39:52\n"
        + "420791121269,15-01-2020 20:54:08,15-01-2020 21:20:43\n"
        + "420721903074,15-01-2020 02:25:21,15-01-2020 05:38:10";

    BigDecimal result = calculator.calculate(input);

    assertEquals(new BigDecimal("2336.2"), result);
  }

  @Test
  void testCalculateWithSinglePhoneNumber() {
    TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();
    String input = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
        "420774577453,13-01-2020 18:20:15,13-01-2020 18:25:57\n" +
        "420774577453,13-01-2020 07:59:20,13-01-2020 08:10:00";

    BigDecimal result = calculator.calculate(input);

    assertEquals(BigDecimal.ZERO, result);
    assertEquals(1,2);
  }

  @Test
  void testCalculateWithCallSpanningTwoDays() {
    TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();
    String input = "420774577455,13-01-2020 23:55:00,14-01-2020 00:10:00\n"
        + "420774577454,13-01-2020 23:58:25,14-01-2020 00:06:40";

    BigDecimal result = calculator.calculate(input);


    assertEquals(new BigDecimal("3.3"), result);
  }
}
