@Test public void should_fail_if_actual_does_not_contain_sequence_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertContainsIgnoringCase(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainIgnoringCase("Yoda","Luke"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
