@Test public void should_fail_if_actual_does_not_contain_given_values_exactly(){
  AssertionInfo info=someInfo();
  long[] expected={6L,8L,20L};
  try {
    arrays.assertContainsExactly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainExactly(actual,expected,newLinkedHashSet(20L),newLinkedHashSet(10L)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
