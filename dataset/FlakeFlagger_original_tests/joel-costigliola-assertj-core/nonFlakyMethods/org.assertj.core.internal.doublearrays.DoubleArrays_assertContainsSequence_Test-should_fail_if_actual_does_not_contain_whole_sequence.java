@Test public void should_fail_if_actual_does_not_contain_whole_sequence(){
  AssertionInfo info=someInfo();
  double[] sequence={6d,20d};
  try {
    arrays.assertContainsSequence(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainSequence(actual,sequence));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
