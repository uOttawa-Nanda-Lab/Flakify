@Test() public void should_fail_if_actual_is_not_an_annotation(){
  AssertionInfo info=someInfo();
  actual=Classes_assertIsAnnotation_Test.class;
  try {
    classes.assertIsAnnotation(someInfo(),actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeAnnotation(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
