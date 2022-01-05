@Test public void should_fail_if_actual_is_not_within_given_hour_of_day(){
  AssertionInfo info=someInfo();
  int hour_of_day=5;
  try {
    dates.assertIsWithinHourOfDay(info,actual,hour_of_day);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"hour",hour_of_day));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
