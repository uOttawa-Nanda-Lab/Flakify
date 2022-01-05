@Test public void should_fail_if_actual_is_not_in_Iterable_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    objectsWithCustomComparisonStrategy.assertIsIn(info,"Luke",values);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeIn("Luke",values,customComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
