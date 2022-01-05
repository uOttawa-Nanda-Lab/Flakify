@Test public void should_fail_if_actual_is_not_in_the_past(){
  AssertionInfo info=someInfo();
  try {
    long oneSecond=1000;
    actual=new Date(System.currentTimeMillis() + oneSecond);
    dates.assertIsInThePast(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeInThePast(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
