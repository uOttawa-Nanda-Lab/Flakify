@Test() public void should_fail_if_fields_are_protected_or_private(){
  AssertionInfo info=someInfo();
  String[] expected=new String[]{"publicField","protectedField","privateField"};
  try {
    classes.assertHasFields(someInfo(),actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveFields(actual,newLinkedHashSet(expected),newLinkedHashSet("protectedField","privateField")));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
