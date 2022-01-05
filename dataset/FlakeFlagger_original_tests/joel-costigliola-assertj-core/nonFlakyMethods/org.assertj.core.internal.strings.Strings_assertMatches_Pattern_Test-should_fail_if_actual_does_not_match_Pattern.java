@Test public void should_fail_if_actual_does_not_match_Pattern(){
  AssertionInfo info=someInfo();
  try {
    strings.assertMatches(info,actual,Pattern.compile("Luke"));
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldMatch(actual,"Luke"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
