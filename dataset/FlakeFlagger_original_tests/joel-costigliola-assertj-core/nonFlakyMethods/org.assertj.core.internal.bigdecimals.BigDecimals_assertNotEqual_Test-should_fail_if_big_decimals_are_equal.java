@Test public void should_fail_if_big_decimals_are_equal(){
  AssertionInfo info=someInfo();
  try {
    bigDecimals.assertNotEqual(info,ONE,ONE);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotBeEqual(ONE,ONE));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
