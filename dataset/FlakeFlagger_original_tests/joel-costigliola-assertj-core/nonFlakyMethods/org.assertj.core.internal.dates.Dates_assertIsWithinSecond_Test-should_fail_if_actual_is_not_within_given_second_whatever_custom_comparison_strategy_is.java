@Test public void should_fail_if_actual_is_not_within_given_second_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  int second=5;
  try {
    datesWithCustomComparisonStrategy.assertIsWithinSecond(info,actual,second);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"second",second));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
