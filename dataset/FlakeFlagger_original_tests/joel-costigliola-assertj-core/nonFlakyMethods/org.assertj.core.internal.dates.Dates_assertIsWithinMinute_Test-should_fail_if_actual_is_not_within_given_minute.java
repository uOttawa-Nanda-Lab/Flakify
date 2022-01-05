@Test public void should_fail_if_actual_is_not_within_given_minute(){
  AssertionInfo info=someInfo();
  int minute=5;
  try {
    dates.assertIsWithinMinute(info,actual,minute);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"minute",minute));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
