@Test public void should_fail_if_actual_is_less_than_other(){
  AssertionInfo info=someInfo();
  try {
    bigDecimals.assertLessThan(info,TEN,ONE);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeLess(TEN,ONE));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
