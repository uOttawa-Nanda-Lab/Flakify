@Test public void should_fail_if_actual_contains_duplicates_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  actual=arrayOf(6d,-8d,6d,-8d);
  try {
    arraysWithCustomComparisonStrategy.assertDoesNotHaveDuplicates(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotHaveDuplicates(actual,newLinkedHashSet(6d,-8d),absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
