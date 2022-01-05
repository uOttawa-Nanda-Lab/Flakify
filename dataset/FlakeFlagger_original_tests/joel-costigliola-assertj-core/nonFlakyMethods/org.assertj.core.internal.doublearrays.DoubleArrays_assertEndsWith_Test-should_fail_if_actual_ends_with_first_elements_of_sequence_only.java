@Test public void should_fail_if_actual_ends_with_first_elements_of_sequence_only(){
  AssertionInfo info=someInfo();
  double[] sequence={6d,20d,22d};
  try {
    arrays.assertEndsWith(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldEndWith(actual,sequence));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
