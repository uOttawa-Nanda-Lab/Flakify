@Test public void should_fail_if_big_decimals_difference_is_greater_than_offset(){
  AssertionInfo info=someInfo();
  try {
    bigDecimals.assertIsCloseTo(info,TEN,ONE,offset(ONE));
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEqual(TEN,ONE,offset(ONE),TEN.subtract(ONE)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
