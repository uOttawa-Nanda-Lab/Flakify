@Test public void should_fail_if_actual_is_not_within_given_day_of_week_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  int day_of_week=SUNDAY;
  try {
    datesWithCustomComparisonStrategy.assertIsWithinDayOfWeek(info,actual,day_of_week);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"day of week",day_of_week));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
