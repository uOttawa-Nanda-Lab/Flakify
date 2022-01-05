@Test public void should_fail_if_actual_does_not_contain_values(){
  AssertionInfo info=someInfo();
  long[] expected={6L,8L,9L};
  try {
    arrays.assertContains(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain(actual,expected,newLinkedHashSet(9L)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
