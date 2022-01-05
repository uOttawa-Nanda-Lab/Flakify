@Test public void should_fail_if_actual_does_not_end_with_suffix_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertEndsWith(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldEndWith("Yoda","Luke",comparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
