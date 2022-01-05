@Test public void should_fail_if_actual_is_not_close_enough_to_expected_value(){
  AssertionInfo info=someInfo();
  try {
    bytes.assertIsCloseTo(info,ONE,TEN,within(ONE));
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEqual(ONE,TEN,within(ONE),(byte)(TEN - ONE)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
