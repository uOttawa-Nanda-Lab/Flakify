@Test public void should_fail_if_actual_contains_given_values_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  double[] expected={6d,-8d,20d};
  try {
    arraysWithCustomComparisonStrategy.assertDoesNotContain(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotContain(actual,expected,newLinkedHashSet(6d,-8d),absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
