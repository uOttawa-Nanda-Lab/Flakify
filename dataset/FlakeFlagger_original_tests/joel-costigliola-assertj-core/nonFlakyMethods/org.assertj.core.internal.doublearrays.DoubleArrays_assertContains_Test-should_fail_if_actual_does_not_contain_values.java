@Test public void should_fail_if_actual_does_not_contain_values(){
  AssertionInfo info=someInfo();
  double[] expected={6d,8d,9d};
  try {
    arrays.assertContains(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain(actual,expected,newLinkedHashSet(9d)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
