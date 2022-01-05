@Test public void should_fail_if_actual_is_not_within_given_month(){
  AssertionInfo info=someInfo();
  int month=5;
  try {
    dates.assertIsWithinMonth(info,actual,month);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"month",month));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
