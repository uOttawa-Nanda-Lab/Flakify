@Test public void should_fail_if_sequence_is_bigger_than_actual_according_to_custom_comparison_strategy(){
  float[] sequence={6f,-8f,10f,12f,20f,22f};
  try {
    AssertionInfo inof=someInfo();
    arraysWithCustomComparisonStrategy.assertStartsWith(inof,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(someInfo(),shouldStartWith(actual,sequence,absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
