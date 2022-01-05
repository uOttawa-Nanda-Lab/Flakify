@Test public void should_fail_if_actual_does_not_contain_given_values_exactly_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  double[] expected={6d,-8d,20d};
  try {
    arraysWithCustomComparisonStrategy.assertContainsExactly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainExactly(actual,expected,newLinkedHashSet(20d),newLinkedHashSet(10d),absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
