@Test public void should_fail_if_actual_does_not_contain_sequence_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertDoesNotContain(info,"Yoda","yoda");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotContain("Yoda","yoda",comparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
