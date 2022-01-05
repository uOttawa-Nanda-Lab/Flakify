@Test public void should_fail_if_actual_contains_given_values(){
  AssertionInfo info=someInfo();
  double[] expected={6d,8d,20d};
  try {
    arrays.assertDoesNotContain(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotContain(actual,expected,newLinkedHashSet(6d,8d)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
