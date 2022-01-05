@Test public void should_fail_if_actual_is_not_within_given_year(){
  AssertionInfo info=someInfo();
  int year=2010;
  try {
    dates.assertIsWithinYear(info,actual,year);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeWithin(actual,"year",year));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
