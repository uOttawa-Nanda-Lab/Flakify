@Test public void should_fail_if_actual_is_not_within_given_day_of_month(){
  AssertionInfo info=someInfo();
  int day_of_month=5;
  try {
    dates.assertIsWithinDayOfMonth(info,actual,day_of_month);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"day of month",day_of_month));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
