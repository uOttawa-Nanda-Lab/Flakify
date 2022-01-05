@Test public void should_fail_if_actual_contains_duplicates(){
  AssertionInfo info=someInfo();
  actual=arrayOf(6d,8d,6d,8d);
  try {
    arrays.assertDoesNotHaveDuplicates(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotHaveDuplicates(actual,newLinkedHashSet(6d,8d)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
