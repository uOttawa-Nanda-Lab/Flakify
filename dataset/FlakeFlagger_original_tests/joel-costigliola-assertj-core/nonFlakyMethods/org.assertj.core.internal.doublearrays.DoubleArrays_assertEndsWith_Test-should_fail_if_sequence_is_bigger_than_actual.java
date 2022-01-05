@Test public void should_fail_if_sequence_is_bigger_than_actual(){
  AssertionInfo info=someInfo();
  double[] sequence={6d,8d,10d,12d,20d,22d};
  try {
    arrays.assertEndsWith(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldEndWith(actual,sequence));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
