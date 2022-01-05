@Test public void should_fail_if_actual_does_not_start_with_sequence(){
  AssertionInfo info=someInfo();
  double[] sequence={8d,10d};
  try {
    arrays.assertStartsWith(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldStartWith(actual,sequence));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
