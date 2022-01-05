@Test public void should_fail_if_actual_is_today_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    Calendar cal=Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH,1);
    Date tomorrow=cal.getTime();
    cal.add(Calendar.DAY_OF_MONTH,-2);
    Date yesterday=cal.getTime();
    actual=monthOf(tomorrow) == monthOf(new Date()) ? tomorrow : yesterday;
    datesWithCustomComparisonStrategy.assertIsInTheFuture(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeInTheFuture(actual,yearAndMonthComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
