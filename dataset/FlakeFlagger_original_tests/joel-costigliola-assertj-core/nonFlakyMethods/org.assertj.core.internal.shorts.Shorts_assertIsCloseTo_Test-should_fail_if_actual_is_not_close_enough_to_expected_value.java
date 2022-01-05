@Test public void should_fail_if_actual_is_not_close_enough_to_expected_value(){
  AssertionInfo info=someInfo();
  try {
    shorts.assertIsCloseTo(info,ONE,TEN,within(ONE));
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEqual(ONE,TEN,within(ONE),(short)(TEN - ONE)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
