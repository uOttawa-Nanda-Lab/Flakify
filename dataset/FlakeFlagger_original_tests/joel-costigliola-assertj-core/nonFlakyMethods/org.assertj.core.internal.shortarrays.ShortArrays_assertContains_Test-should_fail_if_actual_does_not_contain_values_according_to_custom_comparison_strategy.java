@Test public void should_fail_if_actual_does_not_contain_values_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  short[] expected={6,8,9};
  try {
    arraysWithCustomComparisonStrategy.assertContains(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain(actual,expected,newLinkedHashSet((short)9),absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
