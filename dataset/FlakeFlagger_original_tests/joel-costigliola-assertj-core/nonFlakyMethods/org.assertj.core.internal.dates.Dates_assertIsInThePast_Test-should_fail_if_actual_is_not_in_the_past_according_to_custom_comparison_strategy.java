@Test public void should_fail_if_actual_is_not_in_the_past_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    actual=parseDate("2111-01-01");
    datesWithCustomComparisonStrategy.assertIsInThePast(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeInThePast(actual,yearAndMonthComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
