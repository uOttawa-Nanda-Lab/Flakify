@Test public void should_fail_if_actual_does_not_contain_sequence_only_once_according_to_custom_comparison_strategy(){
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
