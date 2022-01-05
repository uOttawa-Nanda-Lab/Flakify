@Test() public void should_fail_if_actual_is_an_annotation(){
  actual=Override.class;
  AssertionInfo info=someInfo();
  try {
    classes.assertIsNotAnnotation(someInfo(),actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotBeAnnotation(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
