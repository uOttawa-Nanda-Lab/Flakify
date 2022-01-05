@Test public void should_fail_if_actual_does_not_contain_values(){
  AssertionInfo info=someInfo();
  char[] expected={'a','b','d'};
  try {
    arrays.assertContains(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain(actual,expected,newLinkedHashSet('d')));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
