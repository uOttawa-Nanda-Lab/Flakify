@Test public void should_fail_if_sequence_is_bigger_than_actual_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  double[] sequence={6d,-8d,10d,12d,20d,22d};
  try {
    arraysWithCustomComparisonStrategy.assertContainsSequence(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainSequence(actual,sequence,absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
