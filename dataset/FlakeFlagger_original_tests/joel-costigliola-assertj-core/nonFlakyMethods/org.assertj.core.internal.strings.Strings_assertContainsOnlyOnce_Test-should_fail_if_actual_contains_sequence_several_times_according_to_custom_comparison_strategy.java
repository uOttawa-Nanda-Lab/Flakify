@Test public void should_fail_if_actual_contains_sequence_several_times_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertContainsOnlyOnce(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyOnce("Yoda","Luke",0,comparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
