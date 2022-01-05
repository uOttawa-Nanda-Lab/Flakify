@Test public void should_fail_if_actual_is_not_within_given_month_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  int month=5;
  try {
    datesWithCustomComparisonStrategy.assertIsWithinMonth(info,actual,month);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"month",month));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
