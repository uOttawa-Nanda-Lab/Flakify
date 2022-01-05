@Test() public void should_fail_if_actual_is_not_an_interface(){
  AssertionInfo info=someInfo();
  actual=Classes_assertIsInterface_Test.class;
  try {
    classes.assertIsInterface(someInfo(),actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeInterface(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
