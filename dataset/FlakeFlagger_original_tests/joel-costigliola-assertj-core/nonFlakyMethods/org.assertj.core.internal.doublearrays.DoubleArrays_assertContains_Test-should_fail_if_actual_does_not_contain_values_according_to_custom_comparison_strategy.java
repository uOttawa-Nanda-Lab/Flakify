@Test public void should_fail_if_actual_does_not_contain_values_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  double[] expected={6d,-8d,9d};
  try {
    arraysWithCustomComparisonStrategy.assertContains(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain(actual,expected,newLinkedHashSet(9d),absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
