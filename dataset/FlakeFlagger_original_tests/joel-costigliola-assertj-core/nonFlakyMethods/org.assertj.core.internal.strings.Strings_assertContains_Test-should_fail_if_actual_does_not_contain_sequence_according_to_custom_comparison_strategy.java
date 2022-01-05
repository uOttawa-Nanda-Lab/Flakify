@Test public void should_fail_if_actual_does_not_contain_sequence_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertContains(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain("Yoda","Luke",comparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
