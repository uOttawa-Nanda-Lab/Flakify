@Test public void should_fail_if_actual_does_not_end_with_suffix(){
  AssertionInfo info=someInfo();
  try {
    strings.assertEndsWith(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldEndWith("Yoda","Luke"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
