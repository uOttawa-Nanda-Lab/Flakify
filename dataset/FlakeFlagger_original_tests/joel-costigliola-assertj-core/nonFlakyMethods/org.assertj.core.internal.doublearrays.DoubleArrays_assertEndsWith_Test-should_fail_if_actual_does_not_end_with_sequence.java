@Test public void should_fail_if_actual_does_not_end_with_sequence(){
  AssertionInfo info=someInfo();
  double[] sequence={20d,22d};
  try {
    arrays.assertEndsWith(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldEndWith(actual,sequence));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
