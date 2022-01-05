@Test public void should_fail_if_actual_ends_with_first_elements_of_sequence_only_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  double[] sequence={6d,20d,22d};
  try {
    arraysWithCustomComparisonStrategy.assertEndsWith(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldEndWith(actual,sequence,absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
