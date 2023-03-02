package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;

public class TheDayMartOffUsingLambda {
  public static void main(String[] args) {
    // 1. 현재날짜 LocalDate는 Temporal 인터페이스의 하위 클래스
    LocalDate today = LocalDate.now();
    System.out.println("today = " + today);
    today = LocalDate.of(2023, 3, 27);

    // TemporalAdjuster 인터페이스의 추상메서드 Temporal adjustInto(Temporal temporal)
    LocalDate martOffDay = today.with(temporal -> {
      // 1. 기준이 되는 날짜를 구한다.
      LocalDate theDay = LocalDate.from(temporal);

      // 2. 두 번째, 네 번째 일요일 날짜를 구한다.
      LocalDate secondDay = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
      LocalDate fourthDay = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));

      // 3. 기준날짜와 비교
      if (theDay.isBefore(secondDay)) {
        return secondDay;
      } else if (theDay.isBefore(fourthDay)) {
        return fourthDay;
      } else {
        return theDay.plusDays(1).with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
      }
    });

    System.out.println("다음 Mart 쉬는 날은 언제? = " + martOffDay);
  }
}
