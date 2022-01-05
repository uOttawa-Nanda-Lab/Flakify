@Test public void should_fail_if_actual_does_not_match_regular_expression(){
  AssertionInfo info=someInfo();
  try {
    strings.assertMatches(info,actual,"Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldMatch(actual,"Luke"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
