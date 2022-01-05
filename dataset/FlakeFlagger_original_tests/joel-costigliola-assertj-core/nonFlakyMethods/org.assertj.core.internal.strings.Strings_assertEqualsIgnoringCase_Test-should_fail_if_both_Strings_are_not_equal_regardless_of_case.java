@Test public void should_fail_if_both_Strings_are_not_equal_regardless_of_case(){
  AssertionInfo info=someInfo();
  try {
    strings.assertEqualsIgnoringCase(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verifyFailureThrownWhenStringsAreNotEqual(info,"Yoda","Luke");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
