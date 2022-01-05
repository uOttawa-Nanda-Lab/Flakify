@Test public void should_fail_if_size_of_actual_is_not_equal_to_expected_size_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  String actual="Han";
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertHasSize(info,actual,6);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSize(actual,actual.length(),6).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
