@Test public void should_fail_if_actual_matches_Pattern(){
  AssertionInfo info=someInfo();
  Pattern pattern=matchAnything();
  try {
    strings.assertDoesNotMatch(info,actual,pattern);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotMatch(actual,pattern.pattern()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
