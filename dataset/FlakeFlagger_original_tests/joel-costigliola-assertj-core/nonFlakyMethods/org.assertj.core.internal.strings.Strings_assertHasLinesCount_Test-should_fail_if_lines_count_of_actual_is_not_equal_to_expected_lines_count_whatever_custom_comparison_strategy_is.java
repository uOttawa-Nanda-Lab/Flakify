@Test public void should_fail_if_lines_count_of_actual_is_not_equal_to_expected_lines_count_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  String actual="Begin" + LINE_SEPARATOR + "End";
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertHasLineCount(info,actual,3);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveLinesCount(actual,2,3).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
