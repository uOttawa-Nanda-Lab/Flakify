@Test public void should_fail_if_fields_are_missing(){
  AssertionInfo info=someInfo();
  String[] expected=new String[]{"missingField","publicField"};
  try {
    classes.assertHasDeclaredFields(someInfo(),actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveDeclaredFields(actual,newLinkedHashSet(expected),newLinkedHashSet("missingField")));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
