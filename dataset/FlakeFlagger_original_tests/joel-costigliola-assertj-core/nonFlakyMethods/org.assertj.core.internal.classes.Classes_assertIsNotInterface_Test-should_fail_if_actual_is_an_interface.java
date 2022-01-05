@Test() public void should_fail_if_actual_is_an_interface(){
  AssertionInfo info=someInfo();
  actual=AssertionInfo.class;
  try {
    classes.assertIsNotInterface(someInfo(),actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotBeInterface(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
