@Test public void should_fail_if_actual_matches_regular_expression(){
  AssertionInfo info=someInfo();
  String regex=matchAnything().pattern();
  try {
    strings.assertDoesNotMatch(info,actual,regex);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotMatch(actual,regex));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
