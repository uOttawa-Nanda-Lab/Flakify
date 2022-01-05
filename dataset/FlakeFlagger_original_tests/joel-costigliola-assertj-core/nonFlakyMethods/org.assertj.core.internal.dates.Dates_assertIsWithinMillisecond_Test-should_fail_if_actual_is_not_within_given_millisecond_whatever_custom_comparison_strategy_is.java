@Test public void should_fail_if_actual_is_not_within_given_millisecond_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  int millisecond=5;
  try {
    datesWithCustomComparisonStrategy.assertIsWithinMillisecond(info,actual,millisecond);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"millisecond",millisecond));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
