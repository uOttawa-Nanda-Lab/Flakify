@Test public void should_fail_if_lines_count_of_actual_is_not_equal_to_expected_size(){
  AssertionInfo info=someInfo();
  String actual="Begin" + LINE_SEPARATOR + "End";
  try {
    strings.assertHasLineCount(info,actual,6);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveLinesCount(actual,2,6).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
