@Test public void should_fail_if_actual_is_not_empty(){
  AssertionInfo info=someInfo();
  try {
    strings.assertEmpty(info,"Yoda");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEmpty("Yoda"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
