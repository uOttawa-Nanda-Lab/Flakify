@Test public void should_fail_if_actual_does_not_contain_given_values_only(){
  AssertionInfo info=someInfo();
  Object[] expected={"Luke","Yoda","Han"};
  try {
    iterables.assertContainsOnly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnly(actual,expected,newLinkedHashSet("Han"),newLinkedHashSet("Leia")));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
